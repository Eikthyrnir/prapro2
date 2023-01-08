package com.timbar.wmi.prapro2.controllers;

import com.timbar.wmi.prapro2.entities.Project;
import com.timbar.wmi.prapro2.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class ProjectController {

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/projects")
    public List<Project> all() {
        return projectRepo.findAll();
    }

}
