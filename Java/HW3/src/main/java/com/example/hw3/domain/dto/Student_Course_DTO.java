package com.example.hw3.domain.dto;

public class Student_Course_DTO {
    String StudentName, CourseName;

    public Student_Course_DTO(String studentName, String courseName) {
        StudentName = studentName;
        CourseName = courseName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }
}
