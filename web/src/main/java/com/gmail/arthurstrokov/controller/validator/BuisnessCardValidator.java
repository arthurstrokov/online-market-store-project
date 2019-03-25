package com.gmail.arthurstrokov.controller.validator;

import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BuisnessCardValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BuisnessCardDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "buisnessCard.title.empty");
        ValidationUtils.rejectIfEmpty(errors, "fullName", "buisnessCard.fullName.empty");
        ValidationUtils.rejectIfEmpty(errors, "workingTelephone", "buisnessCard.workingTelephone.empty");
        BuisnessCardDTO buisnessCard = (BuisnessCardDTO) object;

        if (buisnessCard.getTitle().length() > 40) {
            errors.rejectValue("title", "buisnessCard.title.long.length");
        }
        if (buisnessCard.getTitle().length() > 40) {
            errors.rejectValue("fullName", "buisnessCard.fullName.long.length");
        }
        if (buisnessCard.getTitle().length() > 12) {
            errors.rejectValue("workingTelephone", "buisnessCard.workingTelephone.long.length");
        }
    }
}
