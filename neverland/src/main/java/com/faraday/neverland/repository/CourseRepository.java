package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CourseRepository {

    private final EntityManager em;

    public void persistCourse(Course course) {

        em.persist(course);
    }
}
