package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component("updateUserConverter")
public class UpdateUserConverter implements EntityConverter<User, UserDTO> {

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setSurname(dto.getSurname());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());
        return user;
    }
}
