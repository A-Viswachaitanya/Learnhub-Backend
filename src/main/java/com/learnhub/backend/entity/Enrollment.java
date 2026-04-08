package com.learnhub.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private int progress = 0;

    private Date enrolledDate = new Date();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "enrollment_completed_items", joinColumns = @JoinColumn(name = "enrollment_id"))
    @Column(name = "item_id")
    private java.util.List<Long> completedItems = new java.util.ArrayList<>();
}
