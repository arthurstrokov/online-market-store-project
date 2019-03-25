package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.controller.validator.ProfileValidator;
import com.gmail.arthurstrokov.service.UserService;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final PageProperties pageProperties;
    private final UserService userService;
    private final ProfileValidator profileValidator;

    @Autowired
    public ProfileController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("userService") UserService userService,
            @Qualifier("profileValidator") ProfileValidator profileValidator) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.profileValidator = profileValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('UPDATE_PROFILE')")
    public String getProfile(
            ModelMap modelMap
    ) {
        UserDTO user = userService.findByUserId();
        modelMap.addAttribute("user", user);
        return pageProperties.getProfilePagePath();
    }

    @GetMapping("/profile.update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PROFILE')")
    public String getUpdateProfilePage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getProfileUpdatePagePath();
    }

    @PostMapping("/profile.update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PROFILE')")
    public String updateProfile(
            @ModelAttribute("user") UserDTO user,
            BindingResult bindingResult,
            ModelMap modelMap
    ) {
        profileValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getProfileUpdatePagePath();
        } else {
            userService.update(user);
            return "redirect:/profile/profile.update/{id}";
        }
    }
}
