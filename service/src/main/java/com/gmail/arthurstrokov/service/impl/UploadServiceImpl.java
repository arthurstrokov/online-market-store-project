package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.ItemDao;
import com.gmail.arthurstrokov.dao.model.Item;
import com.gmail.arthurstrokov.service.UploadService;
import com.gmail.arthurstrokov.service.model.Thing;
import com.gmail.arthurstrokov.service.parser.ThingConverter;
import com.gmail.arthurstrokov.service.parser.ThingParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {

    private final ItemDao itemDao;
    private final ThingParser thingParser;
    private final ThingConverter thingConverter;

    @Autowired
    public UploadServiceImpl(
            @Qualifier("itemDao") ItemDao itemDao,
            @Qualifier("thingParser") ThingParser thingParser,
            @Qualifier("thingConverter") ThingConverter thingConverter
    ) {
        this.itemDao = itemDao;
        this.thingParser = thingParser;
        this.thingConverter = thingConverter;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void upload() {
        List<Thing> things = thingParser.parse();
        List<Item> items = thingConverter.convert(things);
        for (Item item : items) {
            itemDao.create(item);
        }
    }
}
