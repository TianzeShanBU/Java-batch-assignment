package com.example.hw3.domain.dto;

public class CourseResponseDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseResponseDTO(String name) {
        this.name = name;
    }
}
