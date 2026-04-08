package com.learnhub.backend.repository;

import com.learnhub.backend.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);
    List<Enrollment> findByCourseId(Long courseId);
    java.util.Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);
}
