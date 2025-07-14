package com.example.stayBuddies.dto.admin;

import lombok.Data;

@Data
public class AdminRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
}
