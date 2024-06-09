package com.example.student.service;

import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {

    Page<Student> search(StudentSearchDTO studentSearchDTO, Pageable pageable);

    Student findById(int id);

    void save(Student student);
}