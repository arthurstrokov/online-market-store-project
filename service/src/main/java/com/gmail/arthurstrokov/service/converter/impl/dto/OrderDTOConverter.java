package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.dao.model.Order;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import com.gmail.arthurstrokov.service.dto.OrderDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("orderDTOConverter")
public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    private final DTOConverter<ItemDTO, Item> itemDTOConverter;
    private final DTOConverter<UserDTO, User> userDTOConverter;

    @Autowired
    public OrderDTOConverter(
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter,
            @Qualifier("userDTOConverter") DTOConverter<UserDTO, User> userDTOConverter) {
        this.itemDTOConverter = itemDTOConverter;
        this.userDTOConverter = userDTOConverter;
    }

    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO order = new OrderDTO();
        order.setId(entity.getId());
        order.setUser(userDTOConverter.toDTO(entity.getUser()));
        order.setItem(itemDTOConverter.toDTO(entity.getItem()));
        order.setCreated(entity.getCreated());
        order.setQuantity(entity.getQuantity());
        order.setStatus(entity.getStatus().name());
        return order;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
