package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.BuisnessCard;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;
import org.springframework.stereotype.Component;

@Component("buisnessCardViewDTOConverter")
public class BuisnessCardViewDTOConverter implements DTOConverter<BuisnessCardDTO, BuisnessCard> {

    @Override
    public BuisnessCardDTO toDTO(BuisnessCard entity) {
        BuisnessCardDTO buisnessCardDTO = new BuisnessCardDTO();
        buisnessCardDTO.setId(entity.getId());
        buisnessCardDTO.setTitle(entity.getTitle());
        buisnessCardDTO.setFullName(entity.getFullName());
        buisnessCardDTO.setWorkingTelephone(entity.getWorkingTelephone());
        return buisnessCardDTO;
    }
}
