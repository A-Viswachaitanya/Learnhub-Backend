package com.learnhub.backend.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CourseDTO {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    private String category;

    private Long userId; 
    private java.util.List<CourseContentDTO> content;
}
