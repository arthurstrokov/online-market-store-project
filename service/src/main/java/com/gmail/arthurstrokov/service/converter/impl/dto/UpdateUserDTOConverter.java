package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component("updateUserDTOConverter")
public class UpdateUserDTOConverter implements DTOConverter<UserDTO, User> {

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setStatus(entity.getStatus());
        return user;
    }
}
