package com.timbar.wmi.prapro2.controllers;

import com.timbar.wmi.prapro2.entities.EmployeeCar;
import com.timbar.wmi.prapro2.repositories.EmployeeCarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class CarController {

    @Autowired
    private EmployeeCarRepo carRepo;

    @GetMapping("/cars")
    public List<EmployeeCar> all() {
        return carRepo.findAll();
    }

}
