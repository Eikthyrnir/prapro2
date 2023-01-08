package com.timbar.wmi.prapro2.controllers;

import com.timbar.wmi.prapro2.entities.Task;
import com.timbar.wmi.prapro2.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> byId(@PathVariable("id") int id) {
        return ResponseEntity.of(taskRepo.findById(id));
    }

    @GetMapping("/tasks")
    public List<Task> all(@RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "size", required = false) Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 5;
        }
        PageRequest pageRequest = PageRequest.of(page, size)
                .withSort(Sort.by("createdAt").descending());
        return taskRepo.findAll(pageRequest).getContent();
    }

    @PostMapping("/task")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createNewTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    @PatchMapping("/task/{id}")
    public ResponseEntity<Task> patchTask(@PathVariable("id") int id,
                                          @RequestBody @Validated Task task) {
        Optional<Task> taskToPatchOptional = taskRepo.findById(id);

        if (taskToPatchOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task taskToPatch = taskToPatchOptional.get();
        if (task.getName() != null) {
            taskToPatch.setName(task.getName());
        }
        if (task.getDescription() != null) {
            taskToPatch.setDescription(task.getDescription());
        }
        if (task.getExecutor() != null) {
            taskToPatch.setExecutor(task.getExecutor());
        }
        return ResponseEntity.ok(taskRepo.save(taskToPatch));
    }

    @DeleteMapping("/task/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") int id) {
        try {
            taskRepo.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            //no such task
        }
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(MethodArgumentNotValidException exception) {
        return exception.getFieldError().toString();
    }

}
