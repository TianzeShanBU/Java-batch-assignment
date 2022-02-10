package com.example.hw3.controller;

import com.example.hw3.domain.dto.StudentResponseDTO;
import com.example.hw3.exception.StudentNotFoundException;
import com.example.hw3.service.StudentService;
import com.example.hw3.service.impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentServiceImpl ss;

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentServiceImpl ss) {
        this.ss = ss;
    }

    @PostMapping("/student")
    public void creatStudent(@RequestBody String name){
        ss.createStudent(name);
    }


    @GetMapping("/student")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() throws StudentNotFoundException {
        return new ResponseEntity<>(ss.getAllStudent(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponseDTO> getAllStudentById(@PathVariable Integer id) throws StudentNotFoundException{
        return new ResponseEntity<>(ss.getStudentById(id), HttpStatus.OK);
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

}
