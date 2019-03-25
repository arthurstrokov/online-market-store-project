package com.gmail.arthurstrokov.service;

import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll(Long page);

    Long countPages(Long quantity);

    void updatePassword(String password, Long userId);

    UserDTO save(UserDTO user);

    void removeById(Long userId);

    UserDTO findByUserId();

    UserDTO findById(Long id);

    void updateRole(Long userRole, Long userId);

    void update(UserDTO user);

    void disableUser(Long userId);

    void enableUser(Long userId);

    User validateByEmail(String email);
}
