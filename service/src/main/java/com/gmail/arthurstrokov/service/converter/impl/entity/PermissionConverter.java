package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Permission;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.PermissionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("permissionEntityConverter")
public class PermissionConverter implements EntityConverter<Permission, PermissionDTO> {

    @Override
    public Permission toEntity(PermissionDTO dto) {
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        return permission;
    }

    @Override
    public List<Permission> toEntityList(List<PermissionDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
