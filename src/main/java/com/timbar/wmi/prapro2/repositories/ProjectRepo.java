package com.timbar.wmi.prapro2.repositories;

import com.timbar.wmi.prapro2.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepo extends CrudRepository<Project, Integer> {

    List<Project> findAll();

}
