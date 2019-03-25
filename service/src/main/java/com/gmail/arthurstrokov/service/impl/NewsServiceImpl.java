package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.NewsDao;
import com.gmail.arthurstrokov.dao.model.News;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.NewsService;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import com.gmail.arthurstrokov.service.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao;
    private final DTOConverter<NewsDTO, News> newsDTOConverter;
    private final EntityConverter<News, NewsDTO> saveNewsConverter;
    private final EntityConverter<News, NewsDTO> updateNewsConverter;

    @Autowired
    public NewsServiceImpl(
            @Qualifier("newsDao") NewsDao newsDao,
            @Qualifier("newsDTOConverter") DTOConverter<NewsDTO, News> newsDTOConverter,
            @Qualifier("saveNewsConverter") EntityConverter<News, NewsDTO> saveNewsConverter,
            @Qualifier("updateNewsConverter") EntityConverter<News, NewsDTO> updateNewsConverter
    ) {
        this.newsDao = newsDao;
        this.newsDTOConverter = newsDTOConverter;
        this.saveNewsConverter = saveNewsConverter;
        this.updateNewsConverter = updateNewsConverter;
    }

    @Override
    public List<NewsDTO> findAll(Long page) {
        List<News> orders = newsDao.getAllNewsForPage(page);
        return newsDTOConverter.toDTOList(orders);
    }

    @Override
    public Long countPages(Long quantity) {
        Long count = newsDao.countAllNews();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }

    @Override
    public void save(NewsDTO news) {
        Long currentUserId = CurrentUser.getCurrentId();
        News convertNews = saveNewsConverter.toEntity(news);
        convertNews.setCreated(LocalDateTime.now());
        User user = new User();
        user.setId(currentUserId);
        convertNews.setUser(user);
        newsDao.create(convertNews);
    }

    @Override
    public void updateNews(NewsDTO news, Long newsId) {
        Long currentUserId = CurrentUser.getCurrentId();
        News findNews = newsDao.findOne(newsId);
        findNews.setContent(news.getContent());
        findNews.setTitle(news.getTitle());
        User user = new User();
        user.setId(currentUserId);
        findNews.setUser(user);
        findNews.setCreated(LocalDateTime.now());
        newsDao.update(findNews);
    }

    @Override
    public void update(NewsDTO news) {
        News updateNews = updateNewsConverter.toEntity(news);
        newsDao.update(updateNews);
    }

    @Override
    public void removeById(Long newsId) {
        newsDao.deleteById(newsId);
    }

    @Override
    public NewsDTO findNews(Long newsId) {
        News news = newsDao.findOne(newsId);
        return newsDTOConverter.toDTO(news);
    }
}
