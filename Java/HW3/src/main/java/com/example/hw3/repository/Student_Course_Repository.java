package com.example.hw3.repository;

import com.example.hw3.domain.entity.Course;
import com.example.hw3.domain.entity.Student;
import com.example.hw3.domain.entity.Student_Course;

import java.util.List;

public interface Student_Course_Repository {
    void register(Integer s_id, Integer c_id);
    public List<Student_Course> getAllStudent_Course();
}
