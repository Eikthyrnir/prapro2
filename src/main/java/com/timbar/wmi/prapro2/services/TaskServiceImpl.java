package com.timbar.wmi.prapro2.services;

import com.timbar.wmi.prapro2.entities.Task;
import com.timbar.wmi.prapro2.repositories.TaskRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Task> getAllTasks(Pageable pageable) {
        return taskRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Task> getAllTasksByExecutorId(Pageable pageable, String executorId) {
        return taskRepo.findByExecutorId(pageable, executorId);
    }

    @Override
    public Optional<Task> getById(int id) {
        return taskRepo.findById(id);
    }

    @Override
    public Task save(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public void deleteById(int id) {
        taskRepo.deleteById(id);
    }

    @Override
    public boolean checkIfExistById(int id) {
        return taskRepo.chackExistanceById(id);
    }
}
