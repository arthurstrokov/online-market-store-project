package com.gmail.arthurstrokov.dao;

import com.gmail.arthurstrokov.dao.model.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {

    void deleteById(long entityId);

    List<News> getAllNewsForPage(Long page);

    Long countAllNews();
}
