package com.timbar.wmi.prapro2.repositories;

import com.timbar.wmi.prapro2.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
}
