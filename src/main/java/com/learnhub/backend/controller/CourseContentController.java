package com.learnhub.backend.controller;

import com.learnhub.backend.dto.CourseContentDTO;
import com.learnhub.backend.entity.Course;
import com.learnhub.backend.entity.CourseContent;
import com.learnhub.backend.repository.CourseContentRepository;
import com.learnhub.backend.repository.CourseRepository;
import com.learnhub.backend.repository.SubmissionRepository;
import com.learnhub.backend.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/course-content")
@RequiredArgsConstructor
public class CourseContentController {
    private final CourseContentRepository contentRepository;
    private final CourseRepository courseRepository;
    private final SubmissionRepository submissionRepository;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<CourseContentDTO> addContent(@RequestBody CourseContentDTO dto) {
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow();
        CourseContent content = modelMapper.map(dto, CourseContent.class);
        content.setCourse(course);
        CourseContent saved = contentRepository.save(content);
        return ResponseEntity.ok(modelMapper.map(saved, CourseContentDTO.class));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseContentDTO>> getContentByCourse(@PathVariable Long courseId) {
        List<CourseContentDTO> dtos = contentRepository.findByCourseId(courseId).stream()
                .map(c -> modelMapper.map(c, CourseContentDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentRepository.findById(id).ifPresent(content -> {
            
            submissionRepository.findByAssignmentId(id).forEach(sub -> {
                try {
                    if (sub.getContent() != null && sub.getContent().contains("/api/files/")) {
                        String studentFilename = sub.getContent().substring(sub.getContent().lastIndexOf("/") + 1);
                        storageService.delete(studentFilename);
                    }
                } catch (Exception ignored) {}
                submissionRepository.delete(sub);
            });
            

            submissionRepository.flush();


            try {
                if (content.getUrl() != null && content.getUrl().contains("/api/files/")) {
                    String filename = content.getUrl().substring(content.getUrl().lastIndexOf("/") + 1);
                    storageService.delete(filename);
                }
            } catch (Exception ignored) {} 
            

            contentRepository.delete(content);
        });
        return ResponseEntity.ok().build();
    }
}
