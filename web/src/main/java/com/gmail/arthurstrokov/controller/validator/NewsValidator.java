package com.gmail.arthurstrokov.controller.validator;

import com.gmail.arthurstrokov.service.dto.NewsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("newsValidator")
public class NewsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NewsDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "news.title.empty");
        ValidationUtils.rejectIfEmpty(errors, "content", "news.content.empty");
        NewsDTO news = (NewsDTO) object;
        if (news.getTitle().length() > 500) {
            errors.rejectValue("title", "news.title.long.length");
        }
        if (news.getContent().length() > 5000) {
            errors.rejectValue("content", "news.content.long.length");
        }
    }
}
