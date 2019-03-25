package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.RoleDao;
import com.gmail.arthurstrokov.dao.model.Role;
import com.gmail.arthurstrokov.service.RoleService;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final DTOConverter<RoleDTO, Role> roleDTOConverter;
    private final EntityConverter<Role, RoleDTO> roleEntityConverter;

    @Autowired
    public RoleServiceImpl(
            @Qualifier("roleDao") RoleDao roleDao,
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter,
            @Qualifier("roleEntityConverter") EntityConverter<Role, RoleDTO> roleEntityConverter
    ) {
        this.roleDao = roleDao;
        this.roleDTOConverter = roleDTOConverter;
        this.roleEntityConverter = roleEntityConverter;
    }

    @Override
    public RoleDTO findByName(RoleDTO role) {
        Role convertRole = roleEntityConverter.toEntity(role);
        roleDao.findOne(role.getId());
        return roleDTOConverter.toDTO(convertRole);
    }

    @Override
    public void save(RoleDTO role) {
        Role convertRole = roleEntityConverter.toEntity(role);
        roleDao.create(convertRole);
    }
}
