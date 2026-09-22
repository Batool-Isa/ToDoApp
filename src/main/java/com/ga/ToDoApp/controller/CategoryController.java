package com.ga.ToDoApp.controller;

import com.ga.ToDoApp.model.Category;
import com.ga.ToDoApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long id){
        return categoryService.getCategory(id);
    }
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category body){
        return categoryService.createCategory(body);
    }
}
