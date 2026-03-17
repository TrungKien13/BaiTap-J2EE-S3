package com.example.kiemtragk.controller;

import com.example.kiemtragk.model.Student;
import com.example.kiemtragk.model.Role;
import com.example.kiemtragk.repository.StudentRepository;
import com.example.kiemtragk.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") Student student) {
        // Vì dùng NoOp nên lưu thẳng mật khẩu chữ thường vào DB
        student.setPassword(student.getPassword());

        // Tự động gán quyền ROLE_STUDENT cho sinh viên mới (Yêu cầu Câu 3)
        Role studentRole = roleRepository.findByName("ROLE_STUDENT");
        if (studentRole != null) {
            Set<Role> roles = new HashSet<>();
            roles.add(studentRole);
            student.setRoles(roles);
        }

        studentRepository.save(student);
        return "redirect:/login?success"; // Đăng ký xong đẩy sang trang Login
    }
}