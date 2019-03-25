package com.gmail.arthurstrokov.controller.validator;

import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("userValidator")
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "surname", "user.surname.empty");
        ValidationUtils.rejectIfEmpty(errors, "profile.phone", "profile.phone.empty");
        ValidationUtils.rejectIfEmpty(errors, "profile.address", "profile.address.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.password.empty");
        UserDTO user = (UserDTO) object;

        Pattern emailPattern = Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        );
        if (!(emailPattern.matcher(user.getEmail()).matches())) {
            errors.rejectValue("email", "user.email.invalid");
        }
        if (user.getName().length() > 20) {
            errors.rejectValue("name", "user.name.long.length");
        }

        if (user.getSurname().length() > 20) {
            errors.rejectValue("surname", "user.surname.long.length");
        }

        if (user.getEmail().length() > 30) {
            errors.rejectValue("email", "user.email.long.length");
        }

        if (user.getProfile().getAddress().length() > 30) {
            errors.rejectValue("profile.address", "user.profile.address.long.length");
        }

        if (user.getProfile().getPhone().length() > 12) {
            errors.rejectValue("profile.phone", "user.profile.phone.long.length");
        }
    }
}
