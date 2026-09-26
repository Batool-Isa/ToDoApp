package com.ga.ToDoApp.controller;

import com.ga.ToDoApp.model.Item;
import com.ga.ToDoApp.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @GetMapping("/categories/{categoryId}/items")
    public List<Item> getItems(@PathVariable("categoryId") Long id) {
        System.out.println("Item controller calling get items");
        return itemService.getItems(id);
    }

    @PostMapping("categories/{categoryId}/items")
    public Item createItem(@PathVariable("categoryId") Long id, @RequestBody Item item) {
        System.out.println("Item controller calling create item");
        return itemService.createItem(id, item);
    }

    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem(@PathVariable("categoryId") Long categoryId, @PathVariable("itemId") Long itemId) {
        System.out.println("Item controller calling getItem");
        return itemService.getItem(categoryId, itemId);
    }
    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(@PathVariable("categoryId") Long categoryId,
                           @PathVariable("itemId") Long itemId,
                           @RequestBody Item item) {
        System.out.println("Item controller calling update item");
        return itemService.updateItem(categoryId, itemId, item);
    }
    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public Item deleteItem(@PathVariable("categoryId") Long categoryId, @PathVariable("itemId") Long itemId) {
        System.out.println("Item controller calling delte Item");
        return itemService.deleteItem(categoryId, itemId);
    }

}
