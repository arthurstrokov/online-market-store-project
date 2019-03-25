package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("updateNewsConverter")
public class UpdateNewsConverter implements EntityConverter<News, NewsDTO> {

    private final EntityConverter<User, UserDTO> updateUserConverter;

    @Autowired
    public UpdateNewsConverter(
            @Qualifier("updateUserConverter") EntityConverter<User, UserDTO> updateUserConverter) {
        this.updateUserConverter = updateUserConverter;
    }

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCreated(dto.getCreated());
        news.setUser(updateUserConverter.toEntity(dto.getUser()));
        return news;
    }
}
