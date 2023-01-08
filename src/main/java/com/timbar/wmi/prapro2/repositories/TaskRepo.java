package com.timbar.wmi.prapro2.repositories;

import com.timbar.wmi.prapro2.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepo extends
        CrudRepository<Task, Integer>,
        PagingAndSortingRepository<Task, Integer> {

    List<Task> findAll();

}
