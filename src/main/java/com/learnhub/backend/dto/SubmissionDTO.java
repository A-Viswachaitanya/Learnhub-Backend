package com.learnhub.backend.dto;
import lombok.Data;
@Data
public class SubmissionDTO {
    private Long id;
    private Long userId;
    private Long assignmentId;
    private String content;
    private String grade;
    private String feedback;
    private String userName;
    private String assignmentTitle;
}
