package com.example.hw3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Student_Course> student_course= new ArrayList<>();

    public List<Student_Course> getStudent_course() {
        return student_course;
    }

    public void setStudent_course(List<Student_Course> student_course) {
        this.student_course = student_course;
    }

    public void add_Student_Course(Student_Course sc){
        this.student_course.add(sc);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}