package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Comment;
import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.CommentDTO;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("newsEntityConverter")
public class NewsConverter implements EntityConverter<News, NewsDTO> {

    private final EntityConverter<User, UserDTO> userEntityConverter;
    private final EntityConverter<Comment, CommentDTO> commentEntityConverter;

    @Autowired
    public NewsConverter(
            @Qualifier("userEntityConverter") EntityConverter<User, UserDTO> userEntityConverter,
            @Qualifier("commentEntityConverter") EntityConverter<Comment, CommentDTO> commentEntityConverter) {
        this.userEntityConverter = userEntityConverter;
        this.commentEntityConverter = commentEntityConverter;
    }

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCreated(dto.getCreated());
        news.setUser(userEntityConverter.toEntity(dto.getUser()));
        news.setComments(commentEntityConverter.toEntitySet(dto.getComments()));
        return news;
    }

    @Override
    public List<News> toEntityList(List<NewsDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
