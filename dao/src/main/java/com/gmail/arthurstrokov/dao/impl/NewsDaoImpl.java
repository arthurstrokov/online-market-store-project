package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.NewsDao;
import com.gmail.arthurstrokov.dao.model.News;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsDao")
@SuppressWarnings("unchecked")
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    public NewsDaoImpl() {
        super(News.class);
    }

    @Override
    public void deleteById(News entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<News> getAllNewsForPage(Long page) {
        String hql = "FROM News as n order by n.created desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public Long countAllNews() {
        String hql = "select count(*) from News as n";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }
}
