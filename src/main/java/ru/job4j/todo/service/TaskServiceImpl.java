package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskStore taskStore;

    @Override
    public Task addTask(Task task) {
        taskStore.addTask(task);
        return task;
    }

    @Override
    public boolean deleteTask(int id) {
        return false;
    }

    @Override
    public boolean updateTask(int id) {
        return false;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskStore.findAllTasks();
    }

    @Override
    public List<Task> findDoneTasks() {
        return null;
    }

    @Override
    public List<Task> findNewTasks() {
        return null;
    }
}
