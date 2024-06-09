package com.example.student.dto;

import com.example.student.model.Clazz;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEditDTO implements Validator {
    private Integer id;
    @NotBlank(message = "Tên không được để trống")
    @Length( max = 50, message = "Tên quá 50 ký tự")
    @Pattern(regexp = "[a-zA-ZÀ-ỹ\\s]*", message = "Tên chỉ chứa khoảng cách hoặc chữ cái")
    private String name;
    @NotBlank(message = "Điểm không được để trống")
    private String score;

    @Valid
    private Clazz clazz;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentEditDTO studentEditDTO = (StudentEditDTO) target;

        if (!studentEditDTO.getScore().trim().equals("")) {
            try {
                double score = Double.parseDouble(studentEditDTO.getScore());
                if(score < 0 || score > 10) {
                    errors.rejectValue("score", "", "Điểm phải trong đoạn từ [0:10]");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("score", "", "Điểm phải là số");
            }
        }
    }
}
