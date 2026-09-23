package com.ga.ToDoApp.service;

import com.ga.ToDoApp.exception.InformationExistException;
import com.ga.ToDoApp.exception.InformationNotFoundException;
import com.ga.ToDoApp.model.Category;
import com.ga.ToDoApp.repository.CategoryRepository;
import org.hibernate.boot.jaxb.hbm.internal.CacheAccessTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("No Category Found With this Id: " + id));
    }

    public Category createCategory(Category catObj) {
        Category cat = categoryRepository.findByName(catObj.getName());
        if (cat != null) {
            throw new InformationExistException("Category with name " + catObj.getName() + " already exist!");
        } else {
            return categoryRepository.save(catObj);
        }
    }

    public Category updateCategory(Long id, Category obj) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + id + " doesn't exist"));
        Category updatedCategory = categoryRepository.findById(id).get();
        updatedCategory.setName(obj.getName());
        updatedCategory.setDescription(obj.getDescription());
        return categoryRepository.save(updatedCategory);

    }

    public Category deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + id + " doesn't exist"));
        categoryRepository.delete(category);
        return category;
    }
}