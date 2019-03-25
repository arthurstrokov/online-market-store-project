package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.service.ItemService;
import com.gmail.arthurstrokov.service.OrderService;
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
@RequestMapping("/items")
public class ItemsController {

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final OrderService orderService;

    @Autowired
    public ItemsController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("itemService") ItemService itemService,
            @Qualifier("orderService") OrderService orderService
    ) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ITEMS')")
    public String getItems(
            ModelMap modelMap,
            @RequestParam(value = "page", defaultValue = "1") Long page
    ) {
        List<ItemDTO> items = itemService.getAvailableItemsForPage(page);
        Long quantity = pageProperties.getQuantityForPage();
        Long pages = itemService.countPagesForAvailableItems(quantity);
        modelMap.addAttribute("items", items);
        modelMap.addAttribute("pages", pages);
        return pageProperties.getItemsPagePath();
    }

    @GetMapping(value = "/{id}/order")
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public String createOrder(
            @PathVariable("id") String id
    ) {
        orderService.save(Long.valueOf(id));
        return "redirect:/items";
    }
}
