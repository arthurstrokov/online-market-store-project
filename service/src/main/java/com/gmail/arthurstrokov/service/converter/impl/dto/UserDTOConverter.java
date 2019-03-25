package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Profile;
import com.gmail.arthurstrokov.dao.model.Role;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.ProfileDTO;
import com.gmail.arthurstrokov.service.dto.RoleDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter;
    private final DTOConverter<RoleDTO, Role> roleDTOConverter;

    @Autowired
    public UserDTOConverter(
            @Qualifier("profileDTOConverter") DTOConverter<ProfileDTO, Profile> profileDTOConverter,
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter
    ) {
        this.profileDTOConverter = profileDTOConverter;
        this.roleDTOConverter = roleDTOConverter;
    }

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setStatus(entity.getStatus());
        user.setProfile(profileDTOConverter.toDTO(entity.getProfile()));
        user.setRole(roleDTOConverter.toDTO(entity.getRole()));
        return user;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
