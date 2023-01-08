package com.timbar.wmi.prapro2.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    @Length(min = 5)
    private String description;
    @Column(insertable = false)
    private ZonedDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee executor;

}
