package com.example.hw3.controller;

import com.example.hw3.domain.dto.Student_Course_DTO;
import com.example.hw3.exception.CourseNotFoundException;
import com.example.hw3.exception.StudentNotFoundException;
import com.example.hw3.service.impl.Student_Course_Service_Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Student_Course_Controller {
    private final Student_Course_Service_Impl sc;

    @Autowired
    public Student_Course_Controller(Student_Course_Service_Impl sc) {
        this.sc = sc;
    }
    private static Logger logger = LoggerFactory.getLogger(Student_Course_Controller.class);

    @PostMapping("/student_course")
    public void register(@RequestBody String str)throws StudentNotFoundException, CourseNotFoundException {
        String[] arr = str.split(",");
        if(arr.length!=2){
            throw new RuntimeException("WRONG_REQUEST_BODY_FORMAT");
        }
        Integer s_id = Integer.valueOf(arr[0]);
        Integer c_id = Integer.valueOf(arr[1]);
        sc.register(s_id,c_id);
    }

    @GetMapping("/student_course")
    public ResponseEntity<List<Student_Course_DTO>> getAllStudentCourse(){
        return new ResponseEntity<>(sc.getAllStudentCourse(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception ex) {
        logger.error("Controller Error",ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> exceptionHandlerStudentNotFound(Exception ex) {
        logger.error("Cannot find student");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> exceptionHandlerCourseNotFound(Exception ex) {
        logger.error("Cannot find course");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
