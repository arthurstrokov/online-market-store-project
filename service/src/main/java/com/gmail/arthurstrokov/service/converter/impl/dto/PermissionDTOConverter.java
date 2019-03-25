package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Permission;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.PermissionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("permissionDTOConverter")
public class PermissionDTOConverter implements DTOConverter<PermissionDTO, Permission> {

    @Override
    public PermissionDTO toDTO(Permission entity) {
        PermissionDTO permission = new PermissionDTO();
        permission.setId(entity.getId());
        permission.setName(entity.getName());
        return permission;
    }

    @Override
    public List<PermissionDTO> toDTOList(List<Permission> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
