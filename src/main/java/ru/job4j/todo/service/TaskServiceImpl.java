package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {
        taskRepository.addTask(task);
        return task;
    }

    @Override
    public boolean deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }

    @Override
    public boolean updateTask(int id) {
        return false;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAllTasks();
    }

    @Override
    public List<Task> findDoneTasks() {
        return taskRepository.findDoneTasks(true);
    }

    @Override
    public List<Task> findUndoneTasks() {
        return taskRepository.findDoneTasks(false);
    }

    @Override
    public Task findTaskById(int id) {
        return taskRepository.findTaskById(id).get();
    }

    @Override
    public boolean changeStatusOfTask(int id, boolean done) {
        return taskRepository.changeStatusOfTask(id, done);
    }

    @Override
    public boolean updateTask(int id, String newTitle, String newDescription) {
        Task task = taskRepository.findTaskById(id).get();
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        return taskRepository.updateTask(task);
    }
}
