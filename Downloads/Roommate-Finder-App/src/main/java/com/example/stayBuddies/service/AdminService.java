package com.example.stayBuddies.service;

import com.example.stayBuddies.dto.admin.AdminRequestDTO;
import com.example.stayBuddies.dto.admin.AdminResponseDTO;
import com.example.stayBuddies.model.Admin;
import java.util.List;
public interface AdminService {
    List<AdminResponseDTO> findAll();
    AdminResponseDTO findById(Long id);
    AdminResponseDTO create(AdminRequestDTO dto);
    AdminResponseDTO update(Long id, AdminRequestDTO dto);
    void delete(Long id);
}