package com.example.kiemtragk.service;

import com.example.kiemtragk.model.Student;
import com.example.kiemtragk.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; // PHẢI IMPORT CÁI NÀY
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService { // QUAN TRỌNG: Phải có implements UserDetailsService

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        if (student == null) {
            throw new UsernameNotFoundException("Không tìm thấy sinh viên!");
        }
        System.out.println("Mat khau lay tu DB: " + student.getPassword());
        return new User(
                student.getUsername(),
                student.getPassword(),
                student.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}