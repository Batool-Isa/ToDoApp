package com.ga.ToDoApp.repository;

import com.ga.ToDoApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
    Category findByNameAndDescription(String name, String description);
    Category findByUserIdAndName(Long id, String categoryName);
    List<Category> findByUserId(Long userId);
}