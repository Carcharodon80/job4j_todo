package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskStore taskStore;

    @Override
    public Task addTask(Task task) {
        taskStore.addTask(task);
        return task;
    }

    @Override
    public boolean deleteTask(int id) {
        return taskStore.deleteTask(id);
    }

    @Override
    public boolean updateTask(int id) {
        return false;
    }

    @Override
    public List<Task> findAllTasks() {
        List<Task> tasks = taskStore.findAllTasks();
        tasks.sort(Comparator.comparingInt(Task::getId));
        return tasks;
    }

    @Override
    public List<Task> findDoneTasks() {
        List<Task> tasks = taskStore.findDoneTasks();
        tasks.sort(Comparator.comparingInt(Task::getId));
        return tasks;
    }

    @Override
    public List<Task> findUndoneTasks() {
        List<Task> tasks = taskStore.findUndoneTasks();
        tasks.sort(Comparator.comparingInt(Task::getId));
        return tasks;
    }

    @Override
    public Task findTaskById(int id) {
        return taskStore.findTaskById(id);
    }

    @Override
    public boolean changeStatusOfTask(int id) {
        Task task = taskStore.findTaskById(id);
        task.setDone(!task.getDone());
        return taskStore.updateTask(task);
    }

    @Override
    public boolean updateDescription(int id, String newDescription) {
        Task task = taskStore.findTaskById(id);
        task.setDescription(newDescription);
        return taskStore.updateTask(task);
    }
}
