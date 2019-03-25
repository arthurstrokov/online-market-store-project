package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Comment;
import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.CommentDTO;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("newsDTOConverter")
public class NewsDTOConverter implements DTOConverter<NewsDTO, News> {

    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Autowired
    public NewsDTOConverter(
            @Qualifier("userDTOConverter") DTOConverter<UserDTO, User> userDTOConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter) {
        this.userDTOConverter = userDTOConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

    @Override
    public NewsDTO toDTO(News entity) {
        NewsDTO news = new NewsDTO();
        news.setId(entity.getId());
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setCreated(entity.getCreated());
        news.setUser(userDTOConverter.toDTO(entity.getUser()));
        news.setComments(commentDTOConverter.toDTOSet(entity.getComments()));
        return news;
    }

    @Override
    public List<NewsDTO> toDTOList(List<News> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}