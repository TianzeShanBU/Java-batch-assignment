package com.example.hw3.repository;

import com.example.hw3.domain.entity.Student;

import java.util.List;

public interface StudentRepository {
    void createStudent(String name);
    Student findStudentById(Integer id);
    List<Student> findAllStudents();

}
