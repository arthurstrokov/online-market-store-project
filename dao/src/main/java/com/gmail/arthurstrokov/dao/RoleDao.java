package com.gmail.arthurstrokov.dao;

import com.gmail.arthurstrokov.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role findByName(String name);
}