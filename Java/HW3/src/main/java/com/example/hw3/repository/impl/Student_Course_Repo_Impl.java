package com.example.hw3.repository.impl;

import com.example.hw3.domain.entity.Course;
import com.example.hw3.domain.entity.Student;
import com.example.hw3.domain.entity.Student_Course;
import com.example.hw3.exception.CourseNotFoundException;
import com.example.hw3.exception.StudentNotFoundException;
import com.example.hw3.repository.Student_Course_Repository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class Student_Course_Repo_Impl implements Student_Course_Repository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void register(Integer s_id, Integer c_id) {
        Query query = em.createQuery("SELECT a from Student a where a.id = " + s_id);
        if(query.getResultList().isEmpty()){
            throw new StudentNotFoundException("STUDENT_NOT_FOUND");
        }
        Student s = (Student) query.getSingleResult();
        Query q = em.createQuery("SELECT a from Course a where a.id = " + c_id);
        if(q.getResultList().isEmpty()){
            throw new CourseNotFoundException("COURSE_NOT_FOUND");
        }
        Course c = (Course) q.getSingleResult();
        Student_Course sc = new Student_Course();
        sc.setCourse(c);
        sc.setStu(s);
        em.persist(sc);
    }

    @Override
    public List<Student_Course> getAllStudent_Course() {
        Query query = em.createQuery("SELECT a from Student_Course a");
        if(query.getResultList().isEmpty()){
            throw new RuntimeException("STUDENT_COURSE_TABLE_IS_EMPTY");
        }
        return query.getResultList();
    }
}
