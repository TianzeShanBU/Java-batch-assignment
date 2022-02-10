package com.example.hw3.service.impl;

import com.example.hw3.domain.dto.StudentResponseDTO;
import com.example.hw3.repository.StudentRepository;
import com.example.hw3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository sr;

    @Autowired
    public StudentServiceImpl(StudentRepository sr) {
        this.sr = sr;
    }

    @Override
    public void createStudent(String name) {
        sr.createStudent(name);
    }

    @Override
    public StudentResponseDTO getStudentById(Integer id) {
        return new StudentResponseDTO(sr.findStudentById(id).getName());
    }

    @Override
    public List<StudentResponseDTO> getAllStudent() {
        return sr.findAllStudents().stream().map(s->new StudentResponseDTO(s.getName())).collect(Collectors.toList());
    }

}
