package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.RoleDao;
import com.gmail.arthurstrokov.dao.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        String hql = "FROM Role as r WHERE r.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }

    @Override
    public void deleteById(Role entityId) {
        throw new UnsupportedOperationException();
    }
}
