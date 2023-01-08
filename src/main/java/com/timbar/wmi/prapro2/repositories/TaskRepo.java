package com.timbar.wmi.prapro2.repositories;

import com.timbar.wmi.prapro2.entities.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepo extends
        CrudRepository<Task, Integer>,
        PagingAndSortingRepository<Task, Integer> {

    List<Task> findAll();

    @Query("select count(t) > 0 from Task t where t.id = :id")
    boolean chackExistanceById(int id);
}
