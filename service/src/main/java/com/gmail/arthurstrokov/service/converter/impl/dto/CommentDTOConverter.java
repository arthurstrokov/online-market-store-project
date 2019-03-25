package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Comment;
import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.CommentDTO;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commentDTOConverter")
public class CommentDTOConverter implements DTOConverter<CommentDTO, Comment> {

    private final DTOConverter<UserDTO, User> userDTOConverter;
    private final DTOConverter<NewsDTO, News> newsCommentDTOConverter;

    @Autowired
    public CommentDTOConverter(DTOConverter<UserDTO, User> userDTOConverter, DTOConverter<NewsDTO, News> newsCommentDTOConverter) {
        this.userDTOConverter = userDTOConverter;
        this.newsCommentDTOConverter = newsCommentDTOConverter;
    }

    @Override
    public CommentDTO toDTO(Comment entity) {
        CommentDTO comment = new CommentDTO();
        comment.setId(entity.getId());
        comment.setContent(entity.getContent());
        comment.setCreated(entity.getCreated());
        comment.setUser(userDTOConverter.toDTO(entity.getUser()));
        comment.setNews(newsCommentDTOConverter.toDTO(entity.getNews()));
        return comment;
    }
}
