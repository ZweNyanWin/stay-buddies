package com.example.stayBuddies.service;

import com.example.stayBuddies.dto.student.StudentResponseDTO;
import com.example.stayBuddies.dto.student.StudentSignupDTO;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> searchUsersByName(String keyword);

    List<StudentResponseDTO> getAllUsers();

    StudentResponseDTO getUserById(Long id);

    StudentResponseDTO createUser(StudentSignupDTO signup);

    StudentResponseDTO updateUser(Long id, StudentSignupDTO update);

    void suspendUser(Long id);

    void deleteUser(Long id);

    StudentResponseDTO findByEmail(String email);

    /**
     * If you hash passwords, also expose a way to get the stored hash.
     */
    String getPasswordHashByEmail(String email);
     // for admin dashboard
    long countAll();

    long countActiveLast30Days();



}
