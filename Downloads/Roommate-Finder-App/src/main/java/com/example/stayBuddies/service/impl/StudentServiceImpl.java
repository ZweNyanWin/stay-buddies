package com.example.stayBuddies.service.impl;

import com.example.stayBuddies.dto.student.StudentResponseDTO;
import com.example.stayBuddies.dto.student.StudentSignupDTO;
import com.example.stayBuddies.mapper.StudentMapper;
import com.example.stayBuddies.model.Student;
import com.example.stayBuddies.repository.StudentRepository;
import com.example.stayBuddies.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository userRepository;
    private final StudentMapper userMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository userRepository,
                              StudentMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper     = userMapper;
    }

    private StudentResponseDTO toResponseDTO(Student u) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        dto.setGender(u.getGender());
        dto.setAge(u.getAge());
        dto.setLocation(u.getLocation());
        dto.setUniversity(u.getUniversity());
        dto.setJoinDate(u.getJoinDate());
        dto.setActive(u.isActive());
        dto.setAlreadyHasRoom(u.isAlreadyHasRoom());
        return dto;
    }

    @Override
    public List<StudentResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getUserById(Long id) {
        Student u = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return toResponseDTO(u);
    }

    @Override
    public StudentResponseDTO createUser(StudentSignupDTO signup) {
        Student user = userMapper.toEntity(signup);
        Student saved = userRepository.save(user);
        return toResponseDTO(saved);
    }

    @Override
    public List<StudentResponseDTO> searchUsersByName(String keyword) {
        return userRepository
                .findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public StudentResponseDTO updateUser(Long id, StudentSignupDTO update) {
        Student u = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));

        // overwrite only the signup fields
        u.setName(update.getName());
        u.setEmail(update.getEmail());
        u.setGender(update.getGender());
        u.setAge(update.getAge());
        u.setLocation(update.getLocation());
        u.setUniversity(update.getUniversity());
        u.setAlreadyHasRoom(update.isAlreadyHasRoom());

        Student saved = userRepository.save(u);
        return toResponseDTO(saved);
    }

    @Override
    public void suspendUser(Long id) {
        Student u = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        u.setActive(false);
        userRepository.save(u);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public StudentResponseDTO findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    @Override
    public String getPasswordHashByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(Student::getPassword)     // got error because I forgot to add the password string in Model
                .orElse(null);
    }

    @Override
    public long countAll() {
        return userRepository.count();
    }

    @Override
    public long countActiveLast30Days() {
        // we assume your User entity has a boolean 'active' and a LocalDate 'joinDate'
        LocalDate cutoff = LocalDate.now().minusDays(30);
        return userRepository.countByActiveTrueAndJoinDateAfter(cutoff);
    }
}
