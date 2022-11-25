package com.example.CRUDApplicationCollabera.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Employees")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String position;
    private int age;
}
