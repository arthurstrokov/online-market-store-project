package com.gmail.arthurstrokov.service.parser;

import com.gmail.arthurstrokov.service.model.Store;
import com.gmail.arthurstrokov.service.model.Thing;
import com.gmail.arthurstrokov.service.properties.FileProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("thingParser")
public class ThingParser {

    private static final Logger logger = LogManager.getLogger(ThingParser.class.getName());
    private final FileProperties fileProperties;

    @Autowired
    public ThingParser(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
    }

    public List<Thing> parse() {
        File file = new File(fileProperties.getItemsFilePath());
        List<Thing> things = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Store.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Store storeItems = (Store) jaxbUnmarshaller.unmarshal(file);
            things = new ArrayList<>(storeItems.getThings());
        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
        }
        return things;
    }
}