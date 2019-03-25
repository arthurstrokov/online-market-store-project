package com.gmail.arthurstrokov.controller.api;

import com.gmail.arthurstrokov.service.ItemService;
import com.gmail.arthurstrokov.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class ItemAPIController {

    private final ItemService itemService;

    @Autowired
    public ItemAPIController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('API')")
    public ItemDTO getItem(@PathVariable(name = "id") Long id) {
        return itemService.findItemById(id);
    }

    @DeleteMapping(value = "/{id}/remove")
    @PreAuthorize("hasAuthority('DELETE_ITEMS')")
    public void removeItemIfNotExists(@PathVariable(name = "id") Long id) {
        itemService.removeIfNotExistsInAnyOrders(id);
    }

    @DeleteMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_ITEMS')")
    public void deleteItem(@PathVariable(name = "id") Long id) {
        itemService.removeById(id);
    }

    @DeleteMapping(value = "/{id}/softDelete")
    @PreAuthorize("hasAuthority('DELETE_ITEMS')")
    public void softDeleteItem(@PathVariable(name = "id") Long id) {
        itemService.softDeleteById(id);
    }

    @DeleteMapping(value = "/{id}/restore")
    @PreAuthorize("hasAuthority('UPDATE_ITEMS')")
    public void restoreItem(@PathVariable(name = "id") Long id) {
        itemService.restoreItem(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public ItemDTO createItem(@RequestBody ItemDTO item) {
        return itemService.save(item);
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('UPDATE_ITEMS')")
    public void updateItem(@RequestBody ItemDTO item) {
        itemService.update(item);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('API')")
    public List<ItemDTO> findAll() {
        return itemService.findAll();
    }
}
