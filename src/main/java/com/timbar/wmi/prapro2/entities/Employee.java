package com.timbar.wmi.prapro2.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String pesel;
    @OneToMany(mappedBy = "executor")
    private List<Task> tasks;
    @OneToOne(mappedBy = "assignedTo")
    private EmployeeCar car;
    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

}
