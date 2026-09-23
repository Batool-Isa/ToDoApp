package com.ga.ToDoApp.repository;

import com.ga.ToDoApp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ItemRepository extends JpaRepository <Item,Long>{
    Item findByName(String name);
    Item findByDescription(String description);
    Item findByDueDate(LocalDate date);
}
