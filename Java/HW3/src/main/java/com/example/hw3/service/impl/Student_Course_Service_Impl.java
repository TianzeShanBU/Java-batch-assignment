package com.example.hw3.service.impl;

import com.example.hw3.domain.dto.Student_Course_DTO;
import com.example.hw3.domain.entity.Course;
import com.example.hw3.domain.entity.Student;
import com.example.hw3.repository.Student_Course_Repository;
import com.example.hw3.service.Student_Course_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Student_Course_Service_Impl implements Student_Course_Service {
    Student_Course_Repository sc;

    @Autowired
    public Student_Course_Service_Impl(Student_Course_Repository sc) {
        this.sc = sc;
    }

    @Override
    public void register(Integer s_id, Integer c_id) {
        sc.register(s_id,c_id);
    }

    @Override
    public List<Student_Course_DTO> getAllStudentCourse() {
        return sc.getAllStudent_Course().stream().
                map(s -> new Student_Course_DTO(s.getStu().getName(),s.getCourse().getName())).
                collect(Collectors.toList());
    }
}
