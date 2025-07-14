// src/main/java/com/example/stayBuddies/mapper/AdminMapper.java
package com.example.stayBuddies.mapper;

import com.example.stayBuddies.dto.admin.AdminRequestDTO;
import com.example.stayBuddies.dto.admin.AdminResponseDTO;
import com.example.stayBuddies.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toEntity(AdminRequestDTO dto) {
        Admin a = new Admin();
        a.setName(dto.getName());
        a.setEmail(dto.getEmail());
        a.setPassword(dto.getPassword());
        a.setPhone(dto.getPhone());
        return a;
    }

    public void updateEntity(AdminRequestDTO dto, Admin a) {
        a.setName(dto.getName());
        a.setEmail(dto.getEmail());
        a.setPassword(dto.getPassword());
        a.setPhone(dto.getPhone());
    }

    public AdminResponseDTO toDTO(Admin a) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(a.getId());
        dto.setName(a.getName());
        dto.setEmail(a.getEmail());
        dto.setPhone(a.getPhone());
        return dto;
    }
}
