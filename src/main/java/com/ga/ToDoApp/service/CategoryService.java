package com.ga.ToDoApp.service;

import com.ga.ToDoApp.exception.InformationExistException;
import com.ga.ToDoApp.exception.InformationNotFoundException;
import com.ga.ToDoApp.model.Category;
import com.ga.ToDoApp.model.User;
import com.ga.ToDoApp.repository.CategoryRepository;
import com.ga.ToDoApp.security.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {
    public static User getCurrentLoggedInUser(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userDetails.getUser();
    }
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
    return categoryRepository.findByUserId(getCurrentLoggedInUser().getId());
    }

    public Category getCategory(Long id) {
        Category cat = categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("No Category Found With this Id: " + id));
        if(!cat.getUser().equals(getCurrentLoggedInUser())){
            throw new InformationNotFoundException("This category doesn't belong to this user");

        }
        return cat;
    }

    public Category createCategory(Category catObj) {
        Category cat = categoryRepository.findByUserIdAndName(getCurrentLoggedInUser().getId(), catObj.getName());
        if (cat != null) {
            throw new InformationExistException("Category with name " + catObj.getName() + " already exist!");
        } else {
            catObj.setUser(getCurrentLoggedInUser());
            return categoryRepository.save(catObj);
        }
    }

    public Category updateCategory(Long id, Category obj) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + id + " doesn't exist"));
        if(!category.getUser().equals(getCurrentLoggedInUser())){
            throw new InformationNotFoundException("This category doesn't belong to this user");

        }
        category.setName(obj.getName());
        category.setDescription(obj.getDescription());
        return categoryRepository.save(category);

    }

    public Category deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + id + " doesn't exist"));
        if(!category.getUser().equals(getCurrentLoggedInUser())){
            throw new InformationNotFoundException("This category doesn't belong to this user");

        }
        categoryRepository.delete(category);
        return category;
    }
}