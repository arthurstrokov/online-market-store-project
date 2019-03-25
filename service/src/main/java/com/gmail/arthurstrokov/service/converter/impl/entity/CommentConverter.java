package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Comment;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component("commentEntityConverter")
public class CommentConverter implements EntityConverter<Comment, CommentDTO> {

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setCreated(dto.getCreated());
        comment.setContent(dto.getContent());
        return comment;
    }
}
