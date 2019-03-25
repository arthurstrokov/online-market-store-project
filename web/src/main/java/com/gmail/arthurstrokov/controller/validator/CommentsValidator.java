package com.gmail.arthurstrokov.controller.validator;

import com.gmail.arthurstrokov.service.dto.CommentDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("commentsValidator")
public class CommentsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CommentDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "content", "comment.content.empty");
        CommentDTO comment = (CommentDTO) object;
        if (comment.getContent().length() > 500) {
            errors.rejectValue("content", "comment.content.long.length");
        }
    }
}
