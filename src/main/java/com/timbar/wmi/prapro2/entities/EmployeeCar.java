package com.timbar.wmi.prapro2.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_cars")
public class EmployeeCar {

    @Id
    private int id;
    private String number;
    private String model;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;

}
