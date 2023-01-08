package com.timbar.wmi.prapro2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    private int id;
    private String name;
    private String description;
//    @ManyToMany(mappedBy = "projects")
//    private List<Employee> executors;

}
