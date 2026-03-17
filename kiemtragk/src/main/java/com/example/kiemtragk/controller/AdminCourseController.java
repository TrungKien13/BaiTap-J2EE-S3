package com.example.kiemtragk.controller;

import com.example.kiemtragk.model.Course;
import com.example.kiemtragk.service.CourseService;
import com.example.kiemtragk.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses") // Mọi link trong này đều bắt đầu bằng /admin/courses
public class AdminCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Trang danh sách quản lý (Có các nút bấm)
    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "admin/course-list";
    }

    // 2. Mở form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/course-add";
    }

    // 3. Mở form sửa (Dựa vào ID)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable(value = "id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/course-edit";
    }

    // 4. Lưu dữ liệu (Dùng chung cho cả Add và Update)
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/admin/courses";
    }

    // 5. Xóa học phần
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable(value = "id") Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/admin/courses";
    }
}