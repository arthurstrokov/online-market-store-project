package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import org.springframework.stereotype.Component;

@Component("newsCommentDTOConverter")
public class NewsCommentDTOConverter implements DTOConverter<NewsDTO, News> {

    @Override
    public NewsDTO toDTO(News entity) {
        NewsDTO news = new NewsDTO();
        news.setId(entity.getId());
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setCreated(entity.getCreated());
        return news;
    }
}
