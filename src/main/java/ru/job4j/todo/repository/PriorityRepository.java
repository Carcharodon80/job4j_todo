package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PriorityRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_ALL = "from Priority order by id";
    private static final String FIND_PRIORITY_BY_ID = "from Priority where id = :id";

    public List<Priority> findAllPriorities() {
        return crudRepository.query(FIND_ALL, Priority.class);
    }

    public Priority findPriorityById(int id) {
        return crudRepository.optional(FIND_PRIORITY_BY_ID, Priority.class, Map.of("id", id)).get();
    }
}
