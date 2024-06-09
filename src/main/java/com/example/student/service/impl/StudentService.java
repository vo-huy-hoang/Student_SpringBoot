package com.example.student.service.impl;

import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;
import com.example.student.repository.IStudentRepository;
import com.example.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Page<Student> search(StudentSearchDTO studentSearchDTO, Pageable pageable) {
        // tránh trường hợp người dùng đi vào màn hình list (chưa search)
        if (studentSearchDTO.getName() == null) {
            studentSearchDTO.setName("");
        }
        if ("".equals(studentSearchDTO.getFromScore())) {
            studentSearchDTO.setFromScore(null);
        }
        if ("".equals(studentSearchDTO.getToScore())) {
            studentSearchDTO.setToScore(null);
        }
        if ("".equals(studentSearchDTO.getClazzId())) {
            studentSearchDTO.setClazzId(null);
        }
        return studentRepository.search(studentSearchDTO.getName(),
                studentSearchDTO.getFromScore() == null ? null : Double.parseDouble(studentSearchDTO.getFromScore()),
                studentSearchDTO.getToScore() == null ? null : Double.parseDouble(studentSearchDTO.getToScore()),
                studentSearchDTO.getClazzId() == null ? null : Integer.parseInt(studentSearchDTO.getClazzId()), pageable);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
        /*
        nếu khoá chính không tồn tại ở DB : thêm mới
        nếu khoá chính trùng với id ở trong DB : cập nhật
         */
    }
}