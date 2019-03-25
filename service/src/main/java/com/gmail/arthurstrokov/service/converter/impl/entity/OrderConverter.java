package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.dao.model.Order;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import com.gmail.arthurstrokov.service.dto.OrderDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("orderEntityConverter")
public class OrderConverter implements EntityConverter<Order, OrderDTO> {

    @Autowired
    @Qualifier("itemEntityConverter")
    private EntityConverter<Item, ItemDTO> itemEntityConverter;
    @Autowired
    @Qualifier("userEntityConverter")
    private EntityConverter<User, UserDTO> userEntityConverter;

    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(userEntityConverter.toEntity(dto.getUser()));
        order.setItem(itemEntityConverter.toEntity(dto.getItem()));
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());
        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
