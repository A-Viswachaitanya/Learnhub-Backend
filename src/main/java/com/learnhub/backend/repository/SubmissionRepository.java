package com.learnhub.backend.repository;
import com.learnhub.backend.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserId(Long userId);
    List<Submission> findByAssignmentId(Long assignmentId);
    List<Submission> findByAssignmentCourseId(Long courseId);
}
