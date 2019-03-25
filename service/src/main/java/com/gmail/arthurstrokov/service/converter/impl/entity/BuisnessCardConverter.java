package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.BuisnessCard;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;
import org.springframework.stereotype.Component;

@Component("buisnessCardConverter")
public class BuisnessCardConverter implements EntityConverter<BuisnessCard, BuisnessCardDTO> {

    @Override
    public BuisnessCard toEntity(BuisnessCardDTO dto) {
        BuisnessCard buisnessCard = new BuisnessCard();
        buisnessCard.setId(dto.getId());
        buisnessCard.setTitle(dto.getTitle());
        buisnessCard.setFullName(dto.getFullName());
        buisnessCard.setWorkingTelephone(dto.getWorkingTelephone());
        return buisnessCard;
    }
}
