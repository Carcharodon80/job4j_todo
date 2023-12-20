package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CategoryRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_ALL_CATEGORIES = "from Category order by id";
    private static final String FIND_CATEGORIES_BY_ID = "from Category where id in :categoriesId";

    public List<Category> findAllCategories() {
        return crudRepository.query(FIND_ALL_CATEGORIES, Category.class);
    }

    public List<Category> findCategoriesById(List<Integer> categories_id) {
        return crudRepository.query(FIND_CATEGORIES_BY_ID, Category.class, Map.of("categoriesId", categories_id));
    }
}
