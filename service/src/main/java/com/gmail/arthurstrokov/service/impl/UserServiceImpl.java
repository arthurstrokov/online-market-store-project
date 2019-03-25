package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.RoleDao;
import com.gmail.arthurstrokov.dao.UserDao;
import com.gmail.arthurstrokov.dao.model.Profile;
import com.gmail.arthurstrokov.dao.model.Role;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.UserService;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.ProfileDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import com.gmail.arthurstrokov.service.exception.UserNotFoundException;
import com.gmail.arthurstrokov.service.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final EntityConverter<User, UserDTO> updateUserConverter;
    private final EntityConverter<Profile, ProfileDTO> profileEntityConverter;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(
            @Qualifier("userDao") UserDao userDao,
            @Qualifier("roleDao") RoleDao roleDao,
            @Qualifier("userDTOConverter") DTOConverter<UserDTO, User> userDTOConverter,
            @Qualifier("updateUserConverter") EntityConverter<User, UserDTO> updateUserConverter,
            @Qualifier("profileEntityConverter") EntityConverter<Profile, ProfileDTO> profileEntityConverter,
            @Qualifier("passwordEncoder") BCryptPasswordEncoder passwordEncoder
    ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.userDTOConverter = userDTOConverter;
        this.updateUserConverter = updateUserConverter;
        this.profileEntityConverter = profileEntityConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll(Long page) {
        List<User> users = userDao.getAllUsersForPage(page);
        return userDTOConverter.toDTOList(users);
    }

    @Override
    public Long countPages(Long quantity) {
        Long count = userDao.countAllUsers();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }

    @Override
    public void removeById(Long entityId) {
        userDao.deleteById(entityId);
    }

    @Override
    public UserDTO findByUserId() {
        Long currentUserId = CurrentUser.getCurrentId();
        User user = userDao.findOne(currentUserId);
        if (user != null) {
            return userDTOConverter.toDTO(user);
        } else {
            throw new UserNotFoundException("User with id= " + currentUserId + " was not found");
        }
    }

    @Override
    public void updatePassword(String password, Long userId) {
        User updateUser = userDao.findOne(userId);
        updateUser.setPassword(passwordEncoder.encode(password));
        userDao.update(updateUser);
    }

    @Override
    public UserDTO save(UserDTO user) {
        User convertUser = updateUserConverter.toEntity(user);
        convertUser.setStatus(true);
        Profile profile = profileEntityConverter.toEntity(user.getProfile());
        profile.setUserId(convertUser.getId());
        profile.setUser(convertUser);
        Role role = roleDao.findByName("CUSTOMER_USER");
        convertUser.setRole(role);
        convertUser.setProfile(profile);
        convertUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(convertUser);
        return userDTOConverter.toDTO(convertUser);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userDao.findOne(id);
        if (user != null) {
            return userDTOConverter.toDTO(user);
        } else {
            throw new UserNotFoundException("User with id= " + id + " was not found");
        }
    }

    @Override
    public void updateRole(Long userRole, Long userId) {
        Role role = new Role();
        role.setId(userRole);
        User user = userDao.findOne(userId);
        user.setRole(role);
        userDao.update(user);
    }

    @Override
    public void update(UserDTO user) {
        Long currentUserId = CurrentUser.getCurrentId();
        user.setId(currentUserId);
        User convertUser = updateUserConverter.toEntity(user);
        User findUser = userDao.findOne(convertUser.getId());
        Profile profile = profileEntityConverter.toEntity(user.getProfile());
        findUser.setName(user.getName());
        findUser.setSurname(user.getSurname());
        findUser.setPassword(passwordEncoder.encode(user.getPassword()));
        findUser.setProfile(profile);
        profile.setUserId(findUser.getId());
        profile.setUser(findUser);
        userDao.update(findUser);
    }

    @Override
    public void disableUser(Long userId) {
        User user = userDao.findOne(userId);
        user.setStatus(false);
        userDao.update(user);
    }

    @Override
    public void enableUser(Long userId) {
        User user = userDao.findOne(userId);
        user.setStatus(true);
        userDao.update(user);
    }

    @Override
    public User validateByEmail(String email) {
        return userDao.validateByEmail(email);
    }
}
