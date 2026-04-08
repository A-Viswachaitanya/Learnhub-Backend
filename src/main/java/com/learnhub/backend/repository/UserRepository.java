package com.learnhub.backend.repository;

import com.learnhub.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Derived query methods based on syllabus
    Optional<User> findByEmail(String email);
}
