package com.example.stayBuddies.service.impl;

import com.example.stayBuddies.dto.admin.AdminRequestDTO;
import com.example.stayBuddies.dto.admin.AdminResponseDTO;
import com.example.stayBuddies.mapper.AdminMapper;
import com.example.stayBuddies.model.Admin;
import com.example.stayBuddies.repository.AdminRepository;
import com.example.stayBuddies.service.AdminService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repo;
    private final AdminMapper mapper;

    public AdminServiceImpl(AdminRepository repo, AdminMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<AdminResponseDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDTO findById(Long id) {
        Admin a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return mapper.toDTO(a);
    }

    @Override
    public AdminResponseDTO create(AdminRequestDTO dto) {
        Admin saved = repo.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Override
    public AdminResponseDTO update(Long id, AdminRequestDTO dto) {
        Admin existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        mapper.updateEntity(dto, existing);
        Admin updated = repo.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
