package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepository {

    private final EntityManager em;

    public void persistCourse(Course course) {

        em.persist(course);
    }

    public List<Course> findRegisterList() {

        return em.createQuery("select c from Course c", Course.class).getResultList();
    }
}
