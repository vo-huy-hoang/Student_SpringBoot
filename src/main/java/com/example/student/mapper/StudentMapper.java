package com.example.student.mapper;

import com.example.student.dto.StudentCreateDTO;
import com.example.student.dto.StudentEditDTO;
import com.example.student.model.Student;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component("studentMapper")
public interface StudentMapper {
    Student toStudentFromStudentCreateDTO(StudentCreateDTO studentCreateDTO);
    Student toStudentFromStudentEditDTO(StudentEditDTO studentEditDTO);
}
