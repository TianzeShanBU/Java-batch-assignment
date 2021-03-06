package com.example.hw3.repository.impl;


import com.example.hw3.domain.entity.Course;
import com.example.hw3.exception.CourseNotFoundException;
import com.example.hw3.exception.StudentNotFoundException;
import com.example.hw3.repository.CourseRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void createCourse(String name) {
        Course c = new Course();
        c.setName(name);
        em.persist(c);
    }

    @Override
    public Course findCourseById(Integer id) {
        Query query = em.createQuery("SELECT a from Course a where a.id = " + id);
        if(query.getResultList().isEmpty()){
            throw new CourseNotFoundException("COURSE_NOT_FOUND");
        }
        Course c = (Course)query.getSingleResult();
        return c;
    }

    @Override
    public List<Course> findAllCourses() {
        Query query = em.createQuery("SELECT a from Course a");
        if(query.getResultList().isEmpty()){
            throw new CourseNotFoundException("LIST_OF_COURSE_NOT_FOUND");
        }
        List<Course> lc = query.getResultList();
        return lc;
    }
}
