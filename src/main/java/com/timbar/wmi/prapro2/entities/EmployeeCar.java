package com.timbar.wmi.prapro2.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_cars")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EmployeeCar {

    @Id
    private int id;
    private String number;
    private String model;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;

}
