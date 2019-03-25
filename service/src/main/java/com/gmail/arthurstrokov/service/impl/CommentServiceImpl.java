package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.CommentDao;
import com.gmail.arthurstrokov.dao.model.Comment;
import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.CommentService;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.CommentDTO;
import com.gmail.arthurstrokov.service.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final EntityConverter<Comment, CommentDTO> saveCommentEntityConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Autowired
    public CommentServiceImpl(
            @Qualifier("commentDao") CommentDao commentDao,
            @Qualifier("saveCommentEntityConverter") EntityConverter<Comment, CommentDTO> saveCommentEntityConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter
    ) {
        this.commentDao = commentDao;
        this.saveCommentEntityConverter = saveCommentEntityConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

    @Override
    public void addComment(String comment, Long newsId) {
        Long currentUserId = CurrentUser.getCurrentId();
        Comment saveComment = new Comment();
        User user = new User();
        user.setId(currentUserId);
        News news = new News();
        news.setId(newsId);
        saveComment.setUser(user);
        saveComment.setNews(news);
        saveComment.setCreated(LocalDateTime.now());
        saveComment.setContent(comment);
        commentDao.create(saveComment);
    }

    @Override
    public void save(CommentDTO comment) {
        Comment saveComment = saveCommentEntityConverter.toEntity(comment);
        commentDao.create(saveComment);
    }

    @Override
    public void removeById(Long commentId) {
        commentDao.deleteById(commentId);
    }

    @Override
    public List<CommentDTO> findComments(Long newsId, Long page) {
        List<Comment> findComments = commentDao.findByNewsId(newsId, page);
        return commentDTOConverter.toDTOList(findComments);
    }

    @Override
    public Long countPages(Long quantity) {
        Long count = commentDao.countAllComments();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }
}
