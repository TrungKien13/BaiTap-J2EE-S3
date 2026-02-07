package BaiTap.Buoi4.controller;

import BaiTap.Buoi4.model.Category;
import BaiTap.Buoi4.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        Category category = categoryService.get(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "category/add";
        }
        return "redirect:/categories";
    }

    @PostMapping("/add")
    public String save(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) return "category/add";

        if (category.getId() == 0) {
            categoryService.add(category);
        } else {
            categoryService.update(category); // Bạn cần thêm hàm update vào Service
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.delete(id); // Bạn cần thêm hàm delete vào Service
        return "redirect:/categories";
    }
}
