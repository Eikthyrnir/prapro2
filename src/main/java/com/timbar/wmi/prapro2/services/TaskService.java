package com.timbar.wmi.prapro2.services;

import com.timbar.wmi.prapro2.entities.Task;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks(Pageable pageable);
    Optional<Task> getById(int id);
    Task save(Task task);
    void deleteById(int id);
    boolean checkIfExistById(int id);

}
