package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Role;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.RoleDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("userEntityConverter")
public class UserConverter implements EntityConverter<User, UserDTO> {

    private final EntityConverter<Role, RoleDTO> roleEntityConverter;

    @Autowired
    public UserConverter(
            @Qualifier("roleEntityConverter") EntityConverter<Role, RoleDTO> roleEntityConverter) {
        this.roleEntityConverter = roleEntityConverter;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setSurname(dto.getSurname());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());
        user.setRole(roleEntityConverter.toEntity(dto.getRole()));
        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
