package com.timbar.wmi.prapro2.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private int id;
    private String name;
    private String description;
    private ZonedDateTime createdTime;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee executor;

}
