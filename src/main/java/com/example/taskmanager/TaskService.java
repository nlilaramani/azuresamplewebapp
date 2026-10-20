package com.example.taskmanager;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public synchronized List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public synchronized void addTask(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Task title cannot be empty");
        }

        tasks.add(new Task(
                idGenerator.getAndIncrement(), title.trim()));
    }

    public synchronized void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}