package com.ga.ToDoApp.service;

import com.ga.ToDoApp.exception.InformationNotFoundException;
import com.ga.ToDoApp.model.Category;
import com.ga.ToDoApp.model.Item;
import com.ga.ToDoApp.repository.CategoryRepository;
import com.ga.ToDoApp.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
@AllArgsConstructor
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;


    public Item createItem(Long id, Item item) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + id + " doesn't exist"));
        item.setCategory(category);
        return itemRepository.save(item);
    }

    public List<Item> getItems(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + id + " doesn't exist"));
        return itemRepository.findByCategoryId(id);
    }

    public Item getItem(Long categoryId, Long itemId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + categoryId + " doesn't exist"));
        List<Item> list = itemRepository.findByCategoryId(categoryId);
        return list.stream().filter(i -> i.getId() == itemId)
                .findFirst().orElseThrow(() -> new InformationNotFoundException("Item with id " + itemId + " doesn't exist"));
    }

    public Item updateItem(Long categoryId, Long itemId, Item item) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + categoryId + " not found"));

        Item fetchedItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new InformationNotFoundException("Item with id " + itemId + " not found"));

        if (!fetchedItem.getCategory().getId().equals(categoryId)) {
            throw new InformationNotFoundException(
                    "Item with id " + itemId +
                            " does not belong to category with id " + categoryId);
        }
        fetchedItem.setName(item.getName());
        fetchedItem.setDescription(item.getDescription());
        fetchedItem.setDueDate(item.getDueDate());
        return itemRepository.save(fetchedItem);

    }

    public Item deleteItem(Long categoryId, Long itemId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + categoryId + " doesn't exist"));
        List<Item> list = itemRepository.findByCategoryId(categoryId);
        Item item = list.stream().filter(i -> i.getId() == itemId)
                .findFirst().orElseThrow(() -> new InformationNotFoundException("Item with id " + itemId + " doesn't exist"));
        if (!item.getCategory().getId().equals(categoryId)) {
            throw new InformationNotFoundException("This item with id " + itemId + " doesn't belong to category with id " + categoryId);
        }
        itemRepository.delete(item);
        return item;
    }
}