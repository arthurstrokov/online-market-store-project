package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.controller.validator.CommentsValidator;
import com.gmail.arthurstrokov.controller.validator.NewsValidator;
import com.gmail.arthurstrokov.service.CommentService;
import com.gmail.arthurstrokov.service.NewsService;
import com.gmail.arthurstrokov.service.dto.CommentDTO;
import com.gmail.arthurstrokov.service.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final CommentService commentService;
    private final NewsValidator newsValidator;
    private final CommentsValidator commentsValidator;

    @Autowired
    public NewsController(
            @Qualifier("newsService") NewsService newsService,
            @Qualifier("commentService") CommentService commentService,
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("commentsValidator") CommentsValidator commentsValidator,
            @Qualifier("newsValidator") NewsValidator newsValidator
    ) {
        this.newsService = newsService;
        this.pageProperties = pageProperties;
        this.commentService = commentService;
        this.commentsValidator = commentsValidator;
        this.newsValidator = newsValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_NEWS')")
    public String getNews(
            ModelMap modelMap,
            @RequestParam(value = "page", defaultValue = "1") Long page
    ) {
        List<NewsDTO> news = newsService.findAll(page);
        Long quantity = pageProperties.getQuantityForPage();
        Long pages = newsService.countPages(quantity);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("pages", pages);
        return pageProperties.getNewsPagePath();
    }

    @GetMapping("/news.create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String getCreateNewsPage(ModelMap modelMap) {
        modelMap.addAttribute("news", new NewsDTO());
        return pageProperties.getNewsCreatePagePath();
    }

    @PostMapping("/news.create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String createNews(
            @ModelAttribute("news") NewsDTO news,
            ModelMap modelMap,
            BindingResult bindingResult
    ) {
        newsValidator.validate(news, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsCreatePagePath();
        } else {
            newsService.save(news);
            return "redirect:/news/news.create";
        }
    }

    @GetMapping("/news.update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String getUpdateNewsPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        NewsDTO news = newsService.findNews(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsUpdatePagePath();
    }

    @PostMapping("/news.update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String updateNews(
            @ModelAttribute("news") NewsDTO news,
            @PathVariable("id") Long newsId,
            ModelMap modelMap,
            BindingResult bindingResult
    ) {
        newsValidator.validate(news, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsUpdatePagePath();
        } else {
            newsService.updateNews(news, newsId);
            return "redirect:/news/news.update/{id}";
        }
    }

    @GetMapping("/news.create.comments/{id}")
    @PreAuthorize("hasAuthority('VIEW_COMMENTS')")
    public String getCreateCommentsPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        NewsDTO news = newsService.findNews(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsCreateCommentsPagePath();
    }

    @PostMapping("/news.create.comments/{id}")
    @PreAuthorize("hasAuthority('CREATE_COMMENTS')")
    public String createComment(
            @PathVariable("id") Long newsId,
            @RequestParam String content
    ) {
        commentService.addComment(content, newsId);
        return "redirect:/news/news.create.comments/{id}";
    }

    @GetMapping("/news.read.comments/{id}")
    @PreAuthorize("hasAuthority('VIEW_COMMENTS')")
    public String getComments(
            ModelMap modelMap,
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "1") Long page
    ) {
        List<CommentDTO> comments = commentService.findComments(id, page);
        Long quantity = pageProperties.getQuantityForPage();
        Long pages = commentService.countPages(quantity);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("pages", pages);
        return pageProperties.getNewsReadCommentsPagePath();
    }

    @GetMapping("/news.read.comments/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_COMMENTS')")
    public String deleteComment(
            @PathVariable("id") String id
    ) {
        commentService.removeById(Long.valueOf(id));
        return "redirect:/news";
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_NEWS')")
    public String deleteNews(
            @PathVariable("id") String id
    ) {
        newsService.removeById(Long.valueOf(id));
        return "redirect:/news";
    }
}
