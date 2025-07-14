package com.example.stayBuddies.repository;

import com.example.stayBuddies.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    List<Student> findByNameContainingIgnoreCase(String keyword);

    Optional<Student> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    long countByActiveTrueAndJoinDateAfter(LocalDate cutoff);
}
