package ru.job4j.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.job4j.todo.model.Task;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Go to http://localhost:8080/tasks/all");
    }
}

// TODO: 21.12.2023 показывать только уникальные значения задач 
// TODO: 21.12.2023 исправить отображение сделанных и несделанных задач 