package com.gmail.arthurstrokov.service;

import com.gmail.arthurstrokov.service.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    void addComment(String comment, Long newsId);

    void save(CommentDTO comment);

    void removeById(Long commentId);

    List<CommentDTO> findComments(Long newsId, Long page);

    Long countPages(Long quantity);
}
