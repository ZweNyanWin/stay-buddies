//package com.example.stayBuddies.mapper;
//
//import com.example.stayBuddies.dto.student.UserSignupDTO;
//import com.example.stayBuddies.model.User;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//public class UserMapper {
//    public User toEntity(UserSignupDTO dto) {
//        User student = new User();
//        student.setName(dto.getName());
//        student.setEmail(dto.getEmail());
//        student.setGender(dto.getGender());
//        student.setAge(dto.getAge());
//        student.setLocation(dto.getLocation());
//        student.setUniversity(dto.getUniversity());
//        student.setAlreadyHasRoom(dto.isAlreadyHasRoom());
//        student.setJoinDate(LocalDate.now());
//        student.setActive(true);
//        return student;
//    }
//}
// src/main/java/com/example/stayBuddies/mapper/UserMapper.java
package com.example.stayBuddies.mapper;

import com.example.stayBuddies.dto.student.StudentSignupDTO;
import com.example.stayBuddies.model.Student;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class StudentMapper {

    public Student toEntity(StudentSignupDTO dto) {
        return Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .gender(dto.getGender())
                .age(dto.getAge())
                .location(dto.getLocation())
                .university(dto.getUniversity())
                .joinDate(LocalDate.now())
                .active(true)
                .alreadyHasRoom(dto.isAlreadyHasRoom())
                .build();
    }
}

