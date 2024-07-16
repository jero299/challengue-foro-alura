package com.challengeBackEnd.ForoAlura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.challengeBackEnd.ForoAlura.model.Course;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
}
