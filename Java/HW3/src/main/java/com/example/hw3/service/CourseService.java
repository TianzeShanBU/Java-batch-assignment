package com.example.hw3.service;

import com.example.hw3.domain.dto.CourseResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    void createCourse(String name);
    CourseResponseDTO findCourseById(Integer id);
    List<CourseResponseDTO> findALlCourse();
}


