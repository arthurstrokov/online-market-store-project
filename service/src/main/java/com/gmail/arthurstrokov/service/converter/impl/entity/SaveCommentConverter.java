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

@Component("saveCommentEntityConverter")
public class SaveCommentConverter implements EntityConverter<Comment, CommentDTO> {

    private final EntityConverter<User, UserDTO> updateUserConverter;
    private final EntityConverter<News, NewsDTO> saveNewsConverter;

    @Autowired
    public SaveCommentConverter(
            @Qualifier("updateUserConverter") EntityConverter<User, UserDTO> updateUserConverter,
            @Qualifier("saveNewsConverter") EntityConverter<News, NewsDTO> saveNewsConverter) {
        this.updateUserConverter = updateUserConverter;
        this.saveNewsConverter = saveNewsConverter;
    }

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());
        comment.setUser(updateUserConverter.toEntity(dto.getUser()));
        comment.setNews(saveNewsConverter.toEntity(dto.getNews()));
        return comment;
    }
}
