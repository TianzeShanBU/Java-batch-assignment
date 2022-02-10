package com.example.hw3.domain.dto;

public class StudentResponseDTO {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentResponseDTO(String name) {
        this.name = name;
    }
}
