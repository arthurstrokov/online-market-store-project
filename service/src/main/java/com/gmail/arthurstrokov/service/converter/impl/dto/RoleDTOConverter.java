package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Permission;
import com.gmail.arthurstrokov.dao.model.Role;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.PermissionDTO;
import com.gmail.arthurstrokov.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("roleDTOConverter")
public class RoleDTOConverter implements DTOConverter<RoleDTO, Role> {

    private final DTOConverter<PermissionDTO, Permission> permissionDTOConverter;

    @Autowired
    public RoleDTOConverter(
            @Qualifier("permissionDTOConverter") DTOConverter<PermissionDTO, Permission> permissionDTOConverter) {
        this.permissionDTOConverter = permissionDTOConverter;
    }

    @Override
    public RoleDTO toDTO(Role entity) {
        RoleDTO role = new RoleDTO();
        role.setId(entity.getId());
        role.setName(entity.getName());
        role.setPermissions(permissionDTOConverter.toDTOSet(entity.getPermissions()));
        return role;
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
