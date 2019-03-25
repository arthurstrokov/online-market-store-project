package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    private Class<T> tClass;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T findOne(long id) {
        return getCurrentSession().get(tClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + tClass.getSimpleName()).list();
    }

    @Override
    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
