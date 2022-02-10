package com.example.hw3.repository;

import com.example.hw3.domain.entity.Course;

import java.util.List;

public interface CourseRepository {
    void createCourse(String name);
    Course findCourseById(Integer id);
    List<Course> findAllCourses();
}
