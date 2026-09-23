package com.ga.ToDoApp.controller;

import com.ga.ToDoApp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemController {
    @Autowired
    private ItemService itemService;
}
