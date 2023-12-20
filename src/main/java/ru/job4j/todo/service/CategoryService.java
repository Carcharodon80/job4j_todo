package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    List<Category> findCategoriesById(List<Integer> categories_id);
}
