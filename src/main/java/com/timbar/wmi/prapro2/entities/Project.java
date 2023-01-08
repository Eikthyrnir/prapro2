package com.timbar.wmi.prapro2.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    private int id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "projects")
    private List<Employee> executors;

}
