package com.example.hw3.controller;

import com.example.hw3.domain.dto.CourseResponseDTO;
import com.example.hw3.domain.entity.Course;
import com.example.hw3.exception.CourseNotFoundException;
import com.example.hw3.exception.StudentNotFoundException;
import com.example.hw3.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService cs;
    private static Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    public CourseController(@Qualifier("courseServiceImpl") CourseService cs) {
        this.cs = cs;
    }

    @PostMapping("/course")
    public void creatCourse(@RequestBody String name){
        cs.createCourse(name);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Integer id)throws CourseNotFoundException {
        return new ResponseEntity<>(cs.findCourseById(id), HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourse()throws CourseNotFoundException{
        return new ResponseEntity<>(cs.findALlCourse(),HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception ex) {
        logger.error("Controller Error",ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> exceptionHandlerCourseNotFound(Exception ex) {
        logger.error("Cannot find course");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
