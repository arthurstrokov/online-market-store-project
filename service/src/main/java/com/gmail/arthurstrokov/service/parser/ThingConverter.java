package com.gmail.arthurstrokov.service.parser;

import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.service.model.Thing;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component("thingConverter")
public class ThingConverter {

    public List<Item> convert(List<Thing> storeItems) {
        List<Item> items = new ArrayList<>();
        for (Thing element : storeItems) {
            Item item = new Item();
            item.setId(element.getId());
            item.setDescription(element.getDescription());
            item.setName(element.getName());
            item.setUniqueNumber(String.valueOf(LocalDateTime.now()));
            item.setPrice(element.getPrice());
            item.setAlive(element.getAlive());
            items.add(item);
        }
        return items;
    }
}
