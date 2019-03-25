package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("itemEntityConverter")
public class ItemConverter implements EntityConverter<Item, ItemDTO> {

    @Override
    public Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setPrice(dto.getPrice());
        item.setAlive(dto.getAlive());
        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
