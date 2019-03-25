package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.dao.model.OrderStatusEnum;
import com.gmail.arthurstrokov.service.OrderService;
import com.gmail.arthurstrokov.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final PageProperties pageProperties;
    private final OrderService orderService;

    @Autowired
    public OrdersController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("orderService") OrderService orderService
    ) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
    }

    @GetMapping("/user.ordered.items")
    @PreAuthorize("hasAuthority('SHOW_ORDERS')")
    public String getOrders(
            ModelMap modelMap
    ) {
        List<OrderDTO> orders = orderService.findOrders();
        modelMap.addAttribute("orders", orders);
        return pageProperties.getUserOrderedItemsPagePath();
    }

    @GetMapping(value = "/user.ordered.items/{id}/delete")
    @PreAuthorize("hasAuthority('SHOW_ORDERS')")
    public String deleteOrder(
            @PathVariable("id") String id
    ) {
        orderService.removeById(Long.valueOf(id));
        return "redirect:/orders/user.ordered.items";
    }

    @GetMapping("/user.orders/{id}")
    @PreAuthorize("hasAuthority('SHOW_USER_ORDERS')")
    public String getOrders(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        List<OrderDTO> orders = orderService.findUserOrders(id);
        modelMap.addAttribute("orders", orders);
        return pageProperties.getUserOrdersPagePath();
    }

    @GetMapping("/user.orders.change.status/{id}")
    @PreAuthorize("hasAuthority('SHOW_USER_ORDERS')")
    public String getOrderChangeStatus(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        OrderDTO order = orderService.findOrder(id);
        modelMap.addAttribute("order", order);
        return pageProperties.getUserOrdersChangeStatusPagePath();
    }

    @PostMapping("/user.orders.change.status/{id}")
    @PreAuthorize("hasAuthority('CHANGE_ORDER_STATUS')")
    public String updateStatus(
            @RequestParam String status,
            @PathVariable("id") Long id,
            @ModelAttribute OrderDTO order
    ) {
        order.setId(id);
        orderService.updateStatus(OrderStatusEnum.valueOf(status), id);
        return "redirect:/orders/user.orders.change.status/{id}";
    }
}
