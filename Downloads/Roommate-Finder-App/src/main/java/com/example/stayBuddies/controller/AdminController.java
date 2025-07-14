package com.example.stayBuddies.controller;

import com.example.stayBuddies.dto.admin.AdminRequestDTO;
import com.example.stayBuddies.dto.admin.AdminResponseDTO;
import com.example.stayBuddies.service.AdminService;
import com.example.stayBuddies.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private final AdminService srv;

    public AdminController(AdminService srv, StudentService userService) {
        this.srv = srv;
    }

    @GetMapping
    public String list(Model m) {
        m.addAttribute("admins", srv.findAll());
        return "admins/admin-management";
    }

    @GetMapping("/new")
    public String createForm(Model m) {
        m.addAttribute("adminRequest", new AdminRequestDTO());
        return "admins/admin-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam(value = "id", required = false) Long id,
            @ModelAttribute("adminRequest") AdminRequestDTO dto
    ) {
        if (id != null) srv.update(id, dto);
        else srv.create(dto);
        return "redirect:/admins";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model m) {
        AdminResponseDTO a = srv.findById(id);
        AdminRequestDTO dto = new AdminRequestDTO();
        dto.setName(a.getName());
        dto.setEmail(a.getEmail());
        dto.setPhone(a.getPhone());
        m.addAttribute("adminRequest", dto);
        m.addAttribute("id", id);
        return "admins/admin-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        srv.delete(id);
        return "redirect:/admins";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model m) {
        m.addAttribute("admin", srv.findById(id));
        return "admins/admin-details";
    }
}
