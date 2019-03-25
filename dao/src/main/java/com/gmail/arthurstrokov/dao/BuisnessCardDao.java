package com.gmail.arthurstrokov.dao;

import com.gmail.arthurstrokov.dao.model.BuisnessCard;

import java.util.List;

public interface BuisnessCardDao extends GenericDao<BuisnessCard> {

    List<BuisnessCard> findBuisnessCardsByUserId(Long userId);

    void deleteById(long entityId);
}
