package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.service.UserService;
import com.gmail.arthurstrokov.service.dto.UpdateUserRoleDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private static final Logger logger = LogManager.getLogger(UsersController.class.getName());
    private final PageProperties pageProperties;
    private final UserService userService;

    @Autowired
    public UsersController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("userService") UserService userService
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUsers(
            ModelMap modelMap,
            @RequestParam(value = "page", defaultValue = "1") Long page
    ) {
        List<UserDTO> users = userService.findAll(page);
        Long quantity = pageProperties.getQuantityForPage();
        Long pages = userService.countPages(quantity);
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("pages", pages);
        return pageProperties.getUsersPagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('SHOW_USER_ORDERS')")
    public String getUserOrders(
            @PathVariable("id") Long id,
            ModelMap modelMap) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserOrdersPagePath();
    }

    @GetMapping("/user.role/{id}")
    @PreAuthorize("hasAuthority('CHANGE_USER_ROLE')")
    public String getUserRole(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserRolePagePath();
    }

    @PostMapping("/user.role/{id}")
    @PreAuthorize("hasAuthority('CHANGE_USER_ROLE')")
    public String updateRole(
            @RequestParam String role,
            @PathVariable("id") Long id,
            @ModelAttribute UpdateUserRoleDTO user
    ) {
        user.setUserId(id);
        try {
            userService.updateRole(Long.valueOf(role), id);
            userService.findById(id);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return "redirect:/users/user.role/{id}";
    }

    @GetMapping("/user.password/{id}")
    @PreAuthorize("hasAuthority('CHANGE_USER_PASSWORD')")
    public String getUserPassword(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserPasswordPagePath();
    }

    @PostMapping("/user.password/{id}")
    @PreAuthorize("hasAuthority('CHANGE_USER_PASSWORD')")
    public String updatePassword(
            @ModelAttribute("user") UserDTO user,
            @PathVariable("id") Long id,
            @RequestParam String password,
            ModelMap modelMap
    ) {
        user.setId(id);
        modelMap.addAttribute("user", user);
        userService.updatePassword(password, id);
        return "redirect:/users/user.password/{id}";
    }

    @GetMapping(value = "/{id}/profile")
    @PreAuthorize("hasAuthority('UPDATE_PROFILE')")
    public String getUserProfile(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getProfilePagePath();
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public String deleteUser(
            @PathVariable("id") String id
    ) {
        userService.removeById(Long.valueOf(id));
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/enable")
    @PreAuthorize("hasAuthority('DISABLE_USER')")
    public String enableUser(
            @PathVariable("id") String id
    ) {
        userService.enableUser(Long.valueOf(id));
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/disable")
    @PreAuthorize("hasAuthority('DISABLE_USER')")
    public String disableUser(
            @PathVariable("id") String id
    ) {
        userService.disableUser(Long.valueOf(id));
        return "redirect:/users";
    }
}
