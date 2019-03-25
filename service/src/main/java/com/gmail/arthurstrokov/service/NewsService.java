package com.gmail.arthurstrokov.service;

import com.gmail.arthurstrokov.service.dto.NewsDTO;

import java.util.List;

public interface NewsService {

    List<NewsDTO> findAll(Long page);

    Long countPages(Long quantity);

    void save(NewsDTO news);

    void updateNews(NewsDTO news, Long newsId);

    void update(NewsDTO news);

    void removeById(Long newsId);

    NewsDTO findNews(Long newsId);
}
