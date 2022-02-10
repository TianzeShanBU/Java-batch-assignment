package com.example.hw3.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="student_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student_Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id")
    private Student stu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id")
    private Course course;

    @Override
    public String toString() {
        return "Student_Course{" +
                "id=" + id +
                ", stu=" + stu +
                ", course=" + course +
                '}';
    }
}