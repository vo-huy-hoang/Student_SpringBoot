package com.example.student.repository;

import com.example.student.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClazzRepository extends JpaRepository<Clazz, Integer> {
    List<Clazz> findAll();
}