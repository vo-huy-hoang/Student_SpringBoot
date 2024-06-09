package com.example.student.model;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động
    @Column(name = "id") // tên cho cột ở trong table
    private int id;

    @Column(name = "name") // tên cho cột ở trong table
    private String name;

    @Column(name = "score") // tên cho cột ở trong table
    private double score;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

}