package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.BuisnessCard;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("buisnessCardDTOConverter")
public class BuisnessCardDTOConverter implements DTOConverter<BuisnessCardDTO, BuisnessCard> {

    private final DTOConverter<UserDTO, User> userDTOConverter;

    @Autowired
    public BuisnessCardDTOConverter(DTOConverter<UserDTO, User> userDTOConverter) {
        this.userDTOConverter = userDTOConverter;
    }

    @Override
    public BuisnessCardDTO toDTO(BuisnessCard entity) {
        BuisnessCardDTO buisnessCardDTO = new BuisnessCardDTO();
        buisnessCardDTO.setId(entity.getId());
        buisnessCardDTO.setTitle(entity.getTitle());
        buisnessCardDTO.setFullName(entity.getFullName());
        buisnessCardDTO.setWorkingTelephone(entity.getWorkingTelephone());
        buisnessCardDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
        return buisnessCardDTO;
    }
}
