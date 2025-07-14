package com.example.stayBuddies.dto.student;

import lombok.Data;

@Data
public class StudentRequestDTO {
    private String name;
    private String email;
    private String gender;
    private Integer age;
    private String location;
    private String university;
    private Boolean alreadyHasRoom;
}
