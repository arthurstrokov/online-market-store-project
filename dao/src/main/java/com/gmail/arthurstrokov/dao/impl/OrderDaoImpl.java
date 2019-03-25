package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.OrderDao;
import com.gmail.arthurstrokov.dao.model.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public void deleteById(Order entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> findOrdersByUserId(Long userId) {
        String hql = "FROM Order as o WHERE o.user.id=:userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }
}
