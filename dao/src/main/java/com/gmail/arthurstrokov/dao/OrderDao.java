package com.gmail.arthurstrokov.dao;

import com.gmail.arthurstrokov.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findOrdersByUserId(Long userId);

    void deleteById(long entityId);
}