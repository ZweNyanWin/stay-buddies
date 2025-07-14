package com.example.stayBuddies.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Integer age;
    private String location;
    private String university;
    private LocalDate joinDate;
    private boolean active;
    private boolean alreadyHasRoom;

}
