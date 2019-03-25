package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("itemDTOConverter")
public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {

    @Override
    public ItemDTO toDTO(Item entity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(entity.getId());
        itemDTO.setName(entity.getName());
        itemDTO.setDescription(entity.getDescription());
        itemDTO.setUniqueNumber(entity.getUniqueNumber());
        itemDTO.setPrice(entity.getPrice());
        itemDTO.setIsAlive(entity.getIsAlive());
        return itemDTO;
    }

    @Override
    public List<ItemDTO> toDTOList(List<Item> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}