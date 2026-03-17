package com.example.kiemtragk.repository;

import com.example.kiemtragk.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}