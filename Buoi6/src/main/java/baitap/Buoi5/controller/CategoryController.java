package baitap.Buoi5.controller;

import baitap.Buoi5.model.Category;
import baitap.Buoi5.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách danh mục
    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/list";
    }

    // Form thêm danh mục
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    // Lưu danh mục
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category/add";
        }
        categoryService.add(category);
        return "redirect:/categories";
    }

    // Xóa danh mục
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}