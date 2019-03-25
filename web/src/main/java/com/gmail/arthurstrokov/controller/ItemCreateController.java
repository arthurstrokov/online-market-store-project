package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.controller.validator.ItemValidator;
import com.gmail.arthurstrokov.service.ItemService;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item.create")
public class ItemCreateController {

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final ItemValidator itemValidator;

    @Autowired
    public ItemCreateController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("itemService") ItemService itemService,
            @Qualifier("itemValidator") ItemValidator itemValidator
    ) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.itemValidator = itemValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String createItem(ModelMap modelMap) {
        modelMap.addAttribute("item", new ItemDTO());
        return pageProperties.getCreateItemPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String createItem(
            @ModelAttribute("item") ItemDTO item,
            ModelMap modelMap,
            BindingResult bindingResult
    ) {
        itemValidator.validate(item, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("item", item);
            return pageProperties.getCreateItemPagePath();
        } else {
            itemService.create(item);
            return "redirect:/item.create";
        }
    }
}
