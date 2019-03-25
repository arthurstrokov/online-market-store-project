package com.gmail.arthurstrokov.service;

import com.gmail.arthurstrokov.dao.model.OrderStatusEnum;
import com.gmail.arthurstrokov.service.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void save(Long itemId);

    List<OrderDTO> findAll();

    List<OrderDTO> findOrders();

    List<OrderDTO> findUserOrders(Long userId);

    void removeById(Long entityId);

    void updateStatus(OrderStatusEnum status, Long orderId);

    OrderDTO findOrder(Long orderId);
}
