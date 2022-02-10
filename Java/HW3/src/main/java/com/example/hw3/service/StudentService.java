package com.example.hw3.service;

import com.example.hw3.domain.dto.StudentResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StudentService {
    public void createStudent(String name);
    public void updateStudent(Integer id, String newName);
    public StudentResponseDTO getStudentById(Integer id);
    public List<StudentResponseDTO> getAllStudent(int size,int pageNum);
}
