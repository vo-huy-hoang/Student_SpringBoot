package com.example.student.repository;

import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    // phụ thuộc vào tên phương thức để truy vấn
    List<Student> findByNameContaining(String name);

    // HQL
    // phụ thuộc vào câu Query
    @Query(value = "from Student s where (:name = '' or :name is null or s.name like concat('%', :name, '%'))" +
            "and (:fromScore is null or s.score >= :fromScore)" +
            "and (:toScore is null or s.score <= :toScore)" +
            "and (:clazzId is null or s.clazz.id = :clazzId)")
    Page<Student> search(@Param("name") String name, @Param("fromScore") Double fromScore, @Param("toScore") Double toScore, @Param("clazzId") Integer clazzId, Pageable pageable);// Phụ phuộc vào @Query để tạo câu truy vấn

    // SỬ dụng nativeQuery => Tùy thuộc vào DB đang dùng => MySQL
//    @Query(value = "select s.id, s.name, s.score, s.clazz_id from Student s where (:name = '' or :name is null or s.name like concat('%', :name, '%'))\n" +
//            "             and (:fromScore = '' or :fromScore is null or s.score >= :fromScore)\n" +
//            "             and (:toScore = '' or :toScore is null or s.score <= :toScore)\n" +
//            "             and (:clazzId = '' or :clazzId is null or s.clazz_id = :clazzId)", nativeQuery = true
//    )
//    List<Student> search2(@Param("name") String name, @Param("fromScore") String fromScore, @Param("toScore") String toScore, @Param("clazzId") String clazzId);

//    Student findById(int id);
//
//    void create(Student student);
}