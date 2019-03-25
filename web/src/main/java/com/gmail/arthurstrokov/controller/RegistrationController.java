package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.service.UserService;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegistrationController {

    private final PageProperties pageProperties;
    private final Validator userValidator;
    private final UserService userService;

    @Autowired
    public RegistrationController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("userValidator") Validator userValidator,
            @Qualifier("userService") UserService userService) {
        this.pageProperties = pageProperties;
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping
    public String getRegistrationPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getRegistrationPagePath();
    }

    @PostMapping
    public String createUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult result,
            ModelMap modelMap
    ) {
        if (userService.validateByEmail(user.getEmail()) != null) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegistrationPagePath();
        } else {
            userValidator.validate(user, result);
            if (result.hasErrors()) {
                modelMap.addAttribute("user", user);
                return pageProperties.getRegistrationPagePath();

            } else {
                userService.save(user);
                return "redirect:/login";
            }
        }
    }
}
