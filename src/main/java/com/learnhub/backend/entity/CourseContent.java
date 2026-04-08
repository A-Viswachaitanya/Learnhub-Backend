package com.learnhub.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_contents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type; // video, text, assignment
    @Column(columnDefinition = "LONGTEXT")
    private String body;
    
    @Column(columnDefinition = "LONGTEXT")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
