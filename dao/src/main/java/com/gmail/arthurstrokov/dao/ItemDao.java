package com.gmail.arthurstrokov.dao;

import com.gmail.arthurstrokov.dao.model.Item;

import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    void deleteById(long entityId);

    List<Item> getAvailableItemsForPage(Long page);

    Long countAvailableItems();

    List<Item> getAllItemsForPage(Long page);

    Long countAllItems();

    Item findBy(String uniqueNumber);
}