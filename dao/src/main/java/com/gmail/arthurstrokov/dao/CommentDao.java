package com.gmail.arthurstrokov.dao;

import com.gmail.arthurstrokov.dao.model.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {

    List<Comment> findByNewsId(Long newsId, Long page);

    Long countAllComments();

    void deleteById(long entityId);
}