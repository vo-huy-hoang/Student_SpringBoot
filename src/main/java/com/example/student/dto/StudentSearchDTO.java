package com.example.student.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSearchDTO {
    private String name;
    private String fromScore;
    private String toScore;
    private String clazzId;

}
