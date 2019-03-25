package com.gmail.arthurstrokov.service;

import com.gmail.arthurstrokov.service.dto.RoleDTO;

public interface RoleService {

    RoleDTO findByName(RoleDTO role);

    void save(RoleDTO role);
}
