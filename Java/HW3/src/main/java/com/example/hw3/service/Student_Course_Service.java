package com.example.hw3.service;

import com.example.hw3.domain.dto.Student_Course_DTO;
import com.example.hw3.domain.entity.Course;
import com.example.hw3.domain.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Student_Course_Service {
    public void register(Integer s_id, Integer c_id);
    public List<Student_Course_DTO> getAllStudentCourse();
}
