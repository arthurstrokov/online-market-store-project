package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class NewsDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private UserDTO user;
    private Set<CommentDTO> comments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(id, newsDTO.id) &&
                Objects.equals(title, newsDTO.title) &&
                Objects.equals(content, newsDTO.content) &&
                Objects.equals(created, newsDTO.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, created);
    }
}