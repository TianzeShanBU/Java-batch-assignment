package com.example.hw3.repository.impl;

import com.example.hw3.domain.entity.Course;
import com.example.hw3.domain.entity.Student;
import com.example.hw3.exception.StudentNotFoundException;
import com.example.hw3.repository.StudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void createStudent(String name) {
        Student s = new Student();
        s.setName(name);
        em.persist(s);
    }

    @Override
    public Student findStudentById(Integer id) {
        Query query = em.createQuery("SELECT a from Student a where a.id = " + id);
        Student s = (Student) query.getSingleResult();
        if(s==null){
            throw new StudentNotFoundException("STUDENT_NOT_FOUND");
        }
        return s;
    }

    @Override
    public List<Student> findAllStudents() {
        Query query = em.createQuery("SELECT a from Student a");
        List<Student> ls = query.getResultList();
        if(ls==null){
            throw new StudentNotFoundException("LIST_OF_STUDENT_NOT_FOUND");
        }
        return ls;
    }
}
