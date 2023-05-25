package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.Comparator;
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
        List<Task> tasks = taskRepository.findAllTasks();
        tasks.sort(Comparator.comparingInt(Task::getId));
        return tasks;
    }

    @Override
    public List<Task> findDoneTasks() {
        List<Task> tasks = taskRepository.findDoneTasks();
        tasks.sort(Comparator.comparingInt(Task::getId));
        return tasks;
    }

    @Override
    public List<Task> findUndoneTasks() {
        List<Task> tasks = taskRepository.findUndoneTasks();
        tasks.sort(Comparator.comparingInt(Task::getId));
        return tasks;
    }

    @Override
    public Task findTaskById(int id) {
        return taskRepository.findTaskById(id);
    }

    @Override
    public boolean changeStatusOfTask(int id) {
        Task task = taskRepository.findTaskById(id);
        task.setDone(!task.getDone());
        return taskRepository.updateTask(task);
    }

    @Override
    public boolean updateDescription(int id, String newDescription) {
        Task task = taskRepository.findTaskById(id);
        task.setDescription(newDescription);
        return taskRepository.updateTask(task);
    }
}
