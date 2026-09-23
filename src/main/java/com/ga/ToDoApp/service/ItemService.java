package com.ga.ToDoApp.service;

import com.ga.ToDoApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
}
