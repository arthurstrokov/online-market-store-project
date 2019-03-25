package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.ItemDao;
import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.dao.model.Order;
import com.gmail.arthurstrokov.service.ItemService;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final DTOConverter<ItemDTO, Item> itemDTOConverter;
    private final EntityConverter<Item, ItemDTO> itemEntityConverter;

    @Autowired
    public ItemServiceImpl(
            @Qualifier("itemDao") ItemDao itemDao,
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter,
            @Qualifier("itemEntityConverter") EntityConverter<Item, ItemDTO> itemEntityConverter
    ) {
        this.itemDao = itemDao;
        this.itemDTOConverter = itemDTOConverter;
        this.itemEntityConverter = itemEntityConverter;
    }

    @Override
    public List<ItemDTO> findAll() {
        List<Item> items = itemDao.findAll();
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public ItemDTO findItemById(Long itemId) {
        Item findItem = itemDao.findOne(itemId);
        return itemDTOConverter.toDTO(findItem);
    }

    @Override
    public void removeById(Long entityId) {
        itemDao.deleteById(entityId);
    }

    @Override
    public void removeIfNotExistsInAnyOrders(Long itemId) {
        Item item = itemDao.findOne(itemId);
        Set<Order> orders = item.getOrders();
        if (orders.isEmpty()) {
            itemDao.deleteById(itemId);
        }
    }

    @Override
    public void softDeleteById(Long itemId) {
        Item item = itemDao.findOne(itemId);
        item.setAlive(false);
        itemDao.update(item);
    }

    @Override
    public void restoreItem(Long itemId) {
        Item item = itemDao.findOne(itemId);
        item.setAlive(true);
        itemDao.update(item);
    }

    @Override
    public void upload(List<ItemDTO> items) {
        List<Item> convertItems = itemEntityConverter.toEntityList(items);
        for (Item item : convertItems) {
            itemDao.create(item);
        }
    }

    @Override
    public ItemDTO save(ItemDTO item) {
        Item convertItem = itemEntityConverter.toEntity(item);
        itemDao.create(convertItem);
        return itemDTOConverter.toDTO(convertItem);
    }

    @Override
    public void create(ItemDTO item) {
        Item convertItem = itemEntityConverter.toEntity(item);
        convertItem.setAlive(true);
        convertItem.setUniqueNumber(String.valueOf(LocalDateTime.now()));
        itemDao.create(convertItem);
    }

    @Override
    public void update(ItemDTO item) {
        Item convertItem = itemEntityConverter.toEntity(item);
        convertItem.setAlive(true);
        Item updateItem = itemDao.findBy(convertItem.getUniqueNumber());
        updateItem.setName(item.getName());
        updateItem.setDescription(item.getDescription());
        updateItem.setPrice(item.getPrice());
        itemDao.update(updateItem);
    }

    @Override
    public void copyItem(Long itemId) {
        Item item = itemDao.findOne(itemId);
        Item copyItem = new Item();
        copyItem.setAlive(true);
        copyItem.setName(item.getName());
        copyItem.setDescription(item.getDescription());
        copyItem.setPrice(item.getPrice());
        copyItem.setUniqueNumber(String.valueOf(LocalDateTime.now()));
        itemDao.create(copyItem);
    }

    @Override
    public List<ItemDTO> getAvailableItemsForPage(Long page) {
        List<Item> items = itemDao.getAvailableItemsForPage(page);
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public Long countPagesForAvailableItems(Long quantity) {
        Long count = itemDao.countAvailableItems();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }

    @Override
    public List<ItemDTO> getAllItems(Long page) {
        List<Item> items = itemDao.getAllItemsForPage(page);
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public Long countPages(Long quantity) {
        Long count = itemDao.countAllItems();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }
}
