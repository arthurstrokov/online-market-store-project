package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Role;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("roleEntityConverter")
public class RoleConverter implements EntityConverter<Role, RoleDTO> {

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
