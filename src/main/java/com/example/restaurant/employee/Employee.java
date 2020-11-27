package com.example.restaurant.employee;

import com.example.restaurant.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "name")
    String name;

    @Column(name = "wage")
    Integer wage;

    @Column(name = "worked_hours")
    Integer workedHours;
}