package com.timbar.wmi.prapro2.repositories;

import com.timbar.wmi.prapro2.entities.EmployeeCar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeCarRepo extends CrudRepository<EmployeeCar, Integer> {

    List<EmployeeCar> findAll();

}
