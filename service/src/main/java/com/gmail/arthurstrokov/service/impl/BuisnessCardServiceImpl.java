package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.BuisnessCardDao;
import com.gmail.arthurstrokov.dao.UserDao;
import com.gmail.arthurstrokov.dao.model.BuisnessCard;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.BuisnessCardService;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;
import com.gmail.arthurstrokov.service.util.CurrentUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buisnessCardService")
@Transactional
public class BuisnessCardServiceImpl implements BuisnessCardService {

    private final BuisnessCardDao buisnessCardDao;
    private final UserDao userDao;
    private final DTOConverter<BuisnessCardDTO, BuisnessCard> buisnessCardDTOConverter;
    private final DTOConverter<BuisnessCardDTO, BuisnessCard> buisnessCardViewDTOConverter;
    private final EntityConverter<BuisnessCard, BuisnessCardDTO> buisnessCardConverter;

    public BuisnessCardServiceImpl(
            @Qualifier("buisnessCardDao") BuisnessCardDao buisnessCardDao,
            @Qualifier("userDao") UserDao userDao,
            @Qualifier("buisnessCardDTOConverter") DTOConverter<BuisnessCardDTO, BuisnessCard> buisnessCardDTOConverter,
            @Qualifier("buisnessCardViewDTOConverter") DTOConverter<BuisnessCardDTO, BuisnessCard> buisnessCardViewDTOConverter,
            @Qualifier("buisnessCardConverter") EntityConverter<BuisnessCard, BuisnessCardDTO> buisnessCardConverter
    ) {
        this.buisnessCardDao = buisnessCardDao;
        this.userDao = userDao;
        this.buisnessCardDTOConverter = buisnessCardDTOConverter;
        this.buisnessCardViewDTOConverter = buisnessCardViewDTOConverter;
        this.buisnessCardConverter = buisnessCardConverter;
    }

    @Override
    public List<BuisnessCardDTO> findAll() {
        List<BuisnessCard> buisnessCards = buisnessCardDao.findAll();
        return buisnessCardDTOConverter.toDTOList(buisnessCards);
    }

    @Override
    public List<BuisnessCardDTO> findAllByUserId() {
        Long currentUserId = CurrentUser.getCurrentId();
        List<BuisnessCard> buisnessCards = buisnessCardDao.findBuisnessCardsByUserId(currentUserId);
        return buisnessCardViewDTOConverter.toDTOList(buisnessCards);
    }

    @Override
    public List<BuisnessCardDTO> findByUserId(Long userId) {
        List<BuisnessCard> buisnessCards = buisnessCardDao.findBuisnessCardsByUserId(userId);
        return buisnessCardViewDTOConverter.toDTOList(buisnessCards);
    }

    @Override
    public void save(BuisnessCardDTO buisnessCardDTO) {
        Long currentUserId = CurrentUser.getCurrentId();
        BuisnessCard buisnessCard = buisnessCardConverter.toEntity(buisnessCardDTO);
        User user = userDao.findOne(currentUserId);
        buisnessCard.setUser(user);
        buisnessCardDao.create(buisnessCard);
    }

    @Override
    public void removeById(Long id) {
        buisnessCardDao.deleteById(id);
    }
}
