package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.service.ItemService;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final PageProperties pageProperties;
    private final ItemService itemService;

    @Autowired
    public StoreController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("itemService") ItemService itemService
    ) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_STORE')")
    public String getItems(ModelMap modelMap,
                           @RequestParam(value = "page", defaultValue = "1") Long page) {
        List<ItemDTO> items = itemService.getAllItems(page);
        Long quantity = pageProperties.getQuantityForPage();
        Long pages = itemService.countPages(quantity);
        modelMap.addAttribute("items", items);
        modelMap.addAttribute("pages", pages);
        return pageProperties.getStorePagePath();
    }

    @GetMapping(value = "/{id}/softdelete")
    @PreAuthorize("hasAuthority('REMOVE_ITEMS')")
    public String softDeleteItem(
            @PathVariable("id") String id
    ) {
        itemService.softDeleteById(Long.valueOf(id));
        return "redirect:/store";
    }

    @GetMapping(value = "/{id}/restore")
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String restoreItem(
            @PathVariable("id") String id
    ) {
        itemService.restoreItem(Long.valueOf(id));
        return "redirect:/store";
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('REMOVE_ITEMS')")
    public String deleteItem(
            @PathVariable("id") String id
    ) {
        itemService.removeIfNotExistsInAnyOrders(Long.valueOf(id));
        return "redirect:/store";
    }

    @GetMapping(value = "/{id}/copy")
    @PreAuthorize("hasAuthority('COPY_ITEMS')")
    public String copyItem(
            @PathVariable("id") String id
    ) {
        itemService.copyItem(Long.valueOf(id));
        return "redirect:/store";
    }
}

