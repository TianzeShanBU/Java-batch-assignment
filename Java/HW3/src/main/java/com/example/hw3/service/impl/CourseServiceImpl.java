package com.example.hw3.service.impl;

import com.example.hw3.domain.dto.CourseResponseDTO;
import com.example.hw3.domain.entity.Course;
import com.example.hw3.repository.CourseRepository;
import com.example.hw3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository rp;

    @Autowired
    public CourseServiceImpl(CourseRepository rp) {
        this.rp = rp;
    }

    @Override
    public void createCourse(String name) {
        rp.createCourse(name);
    }

    @Override
    public CourseResponseDTO findCourseById(Integer id) {
        Course c = rp.findCourseById(id);
        return new CourseResponseDTO(c.getName());
    }

    @Override
    public List<CourseResponseDTO> findALlCourse() {
        return rp.findAllCourses().stream().map(s->new CourseResponseDTO(s.getName())).collect(Collectors.toList());
    }
}
