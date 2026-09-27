package com.ga.ToDoApp.repository;

import com.ga.ToDoApp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository <Item,Long>{
    Item findByName(String name);
    List<Item> findByCategoryId(Long id);
    Item findByDescription(String description);
    Item findByDueDate(LocalDate date);
}
