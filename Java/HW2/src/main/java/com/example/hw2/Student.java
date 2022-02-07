package com.example.hw2;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "student")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "stu")
    private List<Student_Course> student_course = new ArrayList<>();

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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
