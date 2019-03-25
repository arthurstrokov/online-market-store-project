package com.gmail.arthurstrokov.service;

import com.gmail.arthurstrokov.service.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    void upload(List<ItemDTO> items);

    List<ItemDTO> findAll();

    ItemDTO findItemById(Long itemId);

    void removeById(Long itemId);

    void removeIfNotExistsInAnyOrders(Long itemId);

    void softDeleteById(Long itemId);

    void restoreItem(Long itemId);

    ItemDTO save(ItemDTO item);

    void create(ItemDTO item);

    void update(ItemDTO item);

    void copyItem(Long itemId);

    List<ItemDTO> getAvailableItemsForPage(Long page);

    Long countPagesForAvailableItems(Long quantity);

    List<ItemDTO> getAllItems(Long page);

    Long countPages(Long quantity);
}
