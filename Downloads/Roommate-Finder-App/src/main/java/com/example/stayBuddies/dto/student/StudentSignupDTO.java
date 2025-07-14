package com.example.stayBuddies.dto.student;

import lombok.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSignupDTO {
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String gender;
    @Min(18)
    @Max(100)
    private Integer age;
    private String location;
    private String university;
    private boolean alreadyHasRoom;
    private MultipartFile file;
}