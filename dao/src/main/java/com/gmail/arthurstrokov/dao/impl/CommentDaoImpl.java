package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.CommentDao;
import com.gmail.arthurstrokov.dao.model.Comment;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentDao")
@SuppressWarnings("unchecked")
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public void deleteById(Comment entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Comment> findByNewsId(Long newsId, Long page) {
        String hql = "FROM Comment as c WHERE c.news.id=:newsId order by c.created desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        query.setParameter("newsId", newsId);
        return query.getResultList();
    }

    @Override
    public Long countAllComments() {
        String hql = "select count(*) from Comment as c";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }
}
