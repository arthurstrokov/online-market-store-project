package com.gmail.arthurstrokov.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDTO {

    private Long id;
    private String content;
    private LocalDateTime created;
    private UserDTO user;
    private NewsDTO news;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public NewsDTO getNews() {
        return news;
    }

    public void setNews(NewsDTO news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", user=" + user +
                ", news=" + news +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(content, that.content) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, created);
    }
}
