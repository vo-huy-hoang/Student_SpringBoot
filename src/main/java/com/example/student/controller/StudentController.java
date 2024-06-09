package com.example.student.controller;

import com.example.student.dto.StudentCreateDTO;
import com.example.student.dto.StudentEditDTO;
import com.example.student.dto.StudentSearchDTO;
import com.example.student.mapper.StudentMapper;
import com.example.student.model.Clazz;
import com.example.student.model.Student;
import com.example.student.service.IClazzService;
import com.example.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    @Qualifier("studentService") // Chọn triển khai theo tên bean
    private IStudentService studentService;
    @Autowired
    @Qualifier("clazzService")
    private IClazzService clazzService;
    @Autowired
    private StudentMapper studentMapper;
    @ModelAttribute("clazzList")
    public List<Clazz> getClazzList() {
        return clazzService.findAll();
    }
//    @GetMapping("")
//    public ResponseEntity<Page<Student>> getStudents(StudentSearchDTO studentSearchDTO, @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
//        Page<Student> studentList = studentService.search(studentSearchDTO, pageable);
//        return new ResponseEntity<>(studentList, HttpStatus.OK); // 200
//    }
//    @GetMapping("{id}")
//    public ResponseEntity<Student> getStudent(@PathVariable("id") Integer id) {
//        Student student = studentService.findById(id);
//        if (student == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 401
//        }
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("studentCreateDTO", new StudentCreateDTO());
        return "student/create";
    }

    @PostMapping("/create")
    public String create(Model model,
                         @Validated @ModelAttribute("studentCreateDTO") StudentCreateDTO studentCreateDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        new StudentCreateDTO().validate(studentCreateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            // Trường hợp bị lỗi thì gửi lại clazzList
            model.addAttribute("studentCreateDTO", studentCreateDTO);
            return "student/create";
        }

        Student student = studentMapper.toStudentFromStudentCreateDTO(studentCreateDTO);
//        student.setClazz(new Clazz(Integer.parseInt(studentCreateDTO.getClazzId())));
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/student";
    }
//    @PostMapping("")
//    public ResponseEntity<?> create(@Validated @RequestBody StudentCreateDTO studentCreateDTO, BindingResult bindingResult) {
//        new StudentCreateDTO().validate(studentCreateDTO, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
//        }
//
//        Student student = studentMapper.toStudentFromStudentCreateDTO(studentCreateDTO);
//    //        student.setClazz(new Clazz(Integer.parseInt(studentCreateDTO.getClazzId())));
//        studentService.save(student);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/edit")
    public String showEdit(Model model, Integer id) {
        model.addAttribute("studentEditDTO", studentService.findById(id));
        return "student/edit";
    }
    @GetMapping("")
    public String showList(Model model, StudentSearchDTO studentSearchDTO, @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Student> studentList = studentService.search(studentSearchDTO, pageable);
        model.addAttribute("studentList", studentList);
        model.addAttribute("arrayPage", new int[studentList.getTotalPages()]);
        model.addAttribute("sort", pageable.getSort().toString().replace(": ", ","));

        return "student/list";
    }

    

    @PostMapping("/edit")
    public String edit(Model model, @Validated @ModelAttribute("studentEditDTO") StudentEditDTO studentEditDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new StudentEditDTO().validate(studentEditDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            // Trường hợp bị lỗi thì gửi lại clazzList
            model.addAttribute("studentEditDTO", studentEditDTO);
            return "student/edit";
        }

        Student student = studentMapper.toStudentFromStudentEditDTO(studentEditDTO);
//        student.setClazz(new Clazz(Integer.parseInt(studentEditDTO.getClazzId())));

        studentService.save(student);

        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công");
        return "redirect:/student";
    }

////    private void eidtStudent(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Student student = studentService.findById(id);
//        student.setName(request.getParameter("name"));
//        student.setScore(Double.parseDouble(request.getParameter("score")));
//    }

}