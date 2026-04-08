package com.learnhub.backend.dto;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private CourseDTO course;
    private int progress;
    private java.util.List<Long> completedItems;
}
