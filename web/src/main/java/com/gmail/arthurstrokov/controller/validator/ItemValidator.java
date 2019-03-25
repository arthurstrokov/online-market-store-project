package com.gmail.arthurstrokov.controller.validator;

import com.gmail.arthurstrokov.service.dto.ItemDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ItemDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "item.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "item.description.empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "item.price.empty");
        ItemDTO item = (ItemDTO) object;
        if (item.getPrice() == null) {
            errors.rejectValue("price", "item.price.invalid");
        } else {
            Pattern pattern = Pattern.compile(
                    "^[0-9]+(\\.[0-9]{1,2})?$",
                    Pattern.CASE_INSENSITIVE
            );
            if (!(pattern.matcher(item.getPrice().toString()).matches())) {
                errors.rejectValue("price", "item.price.invalid");
            }
        }
        if (item.getName().length() > 50) {
            errors.rejectValue("name", "item.name.long.length");
        }
        if (item.getDescription().length() > 500) {
            errors.rejectValue("description", "item.description.long.length");
        }
        if (item.getPrice() != null) {
            if (item.getPrice().toString().length() > 10) {
                errors.rejectValue("price", "item.price.long.length");
            }
        }
    }
}
