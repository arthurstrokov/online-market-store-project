package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.OrderDao;
import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.dao.model.Order;
import com.gmail.arthurstrokov.dao.model.OrderStatusEnum;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.OrderService;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.OrderDTO;
import com.gmail.arthurstrokov.service.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final DTOConverter<OrderDTO, Order> orderDTOConverter;

    @Autowired
    public OrderServiceImpl(
            @Qualifier("orderDao") OrderDao orderDao,
            @Qualifier("orderDTOConverter") DTOConverter<OrderDTO, Order> orderDTOConverter
    ) {
        this.orderDao = orderDao;
        this.orderDTOConverter = orderDTOConverter;
    }

    @Override
    public void save(Long itemId) {
        Long currentUserId = CurrentUser.getCurrentId();
        User user = new User();
        Item item = new Item();
        user.setId(currentUserId);
        item.setId(itemId);
        Order order = new Order();
        order.setUser(user);
        order.setItem(item);
        order.setCreated(LocalDateTime.now());
        order.setStatus(OrderStatusEnum.NEW);
        orderDao.create(order);
    }

    @Override
    public List<OrderDTO> findOrders() {
        Long currentUserId = CurrentUser.getCurrentId();
        List<Order> orders = orderDao.findOrdersByUserId(currentUserId);
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    public List<OrderDTO> findUserOrders(Long userId) {
        List<Order> orders = orderDao.findOrdersByUserId(userId);
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderDao.findAll();
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    public void removeById(Long entityId) {
        orderDao.deleteById(entityId);
    }

    @Override
    public void updateStatus(OrderStatusEnum status, Long orderId) {
        Order order = orderDao.findOne(orderId);
        order.setStatus(status);
        orderDao.update(order);
    }

    @Override
    public OrderDTO findOrder(Long orderId) {
        Order order = orderDao.findOne(orderId);
        return orderDTOConverter.toDTO(order);
    }
}