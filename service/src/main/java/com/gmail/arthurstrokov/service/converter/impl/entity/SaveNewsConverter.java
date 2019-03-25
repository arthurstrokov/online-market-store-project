package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import org.springframework.stereotype.Component;

@Component("saveNewsConverter")
public class SaveNewsConverter implements EntityConverter<News, NewsDTO> {

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCreated(dto.getCreated());
        return news;
    }
}
