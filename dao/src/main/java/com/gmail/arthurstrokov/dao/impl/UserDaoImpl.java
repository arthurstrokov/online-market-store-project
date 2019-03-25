package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.UserDao;
import com.gmail.arthurstrokov.dao.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public void deleteById(User entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User validateByEmail(String email) {
        String hql = "FROM User u WHERE u.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> getAllUsersForPage(Long page) {
        String hql = "FROM User as u order by u.id";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public Long countAllUsers() {
        String hql = "select count(*) from User as u";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }
}