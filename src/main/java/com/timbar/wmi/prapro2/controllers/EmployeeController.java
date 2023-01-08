package com.timbar.wmi.prapro2.controllers;

import com.timbar.wmi.prapro2.entities.Employee;
import com.timbar.wmi.prapro2.repositories.EmployeeCarRepo;
import com.timbar.wmi.prapro2.repositories.EmployeeRepo;
import com.timbar.wmi.prapro2.repositories.ProjectRepo;
import com.timbar.wmi.prapro2.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> byId(@PathVariable("id") int id) {
        return ResponseEntity.of(employeeRepo.findById(id));
    }


}
