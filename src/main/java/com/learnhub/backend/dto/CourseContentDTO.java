package com.learnhub.backend.dto;
import lombok.Data;
@Data
public class CourseContentDTO {
    private Long id;
    private String title;
    private String type;
    private String body;
    private String url;
    private Long courseId;
}
