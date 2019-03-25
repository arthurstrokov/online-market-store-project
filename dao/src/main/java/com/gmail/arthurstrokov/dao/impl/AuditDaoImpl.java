package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.AuditDao;
import com.gmail.arthurstrokov.dao.model.Audit;
import org.springframework.stereotype.Repository;

@Repository("auditDao")
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl() {
        super(Audit.class);
    }

    @Override
    public void deleteById(Audit entityId) {
        throw new UnsupportedOperationException();
    }
}
