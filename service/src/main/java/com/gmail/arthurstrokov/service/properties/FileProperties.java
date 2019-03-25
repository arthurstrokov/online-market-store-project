package com.gmail.arthurstrokov.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("fileProperties")
public class FileProperties {

    @Value("${items.file.path}")
    private String itemsFilePath;

    public String getItemsFilePath() {
        return itemsFilePath;
    }
}