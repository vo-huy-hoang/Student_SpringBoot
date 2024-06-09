package com.example.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động
    @Column(name = "id") // tên cho cột ở trong table
    private Integer id;

    @Column(name = "name") // tên cho cột ở trong table
    private String name;

    public Clazz(Integer id) {
        this.id = id;
    }
}