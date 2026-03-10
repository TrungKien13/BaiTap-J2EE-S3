package baitap.Buoi5.controller;

import baitap.Buoi5.model.Product;
import baitap.Buoi5.service.CategoryService;
import baitap.Buoi5.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // Dòng này cực kỳ quan trọng để hết lỗi MultipartFile

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product/products";
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "product/add";
    }

    // Xử lý Lưu sản phẩm (Hỗ trợ cả URL và Upload File)
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult result,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/add";
        }


        if (imageFile != null && !imageFile.isEmpty()) {
            try {

                String uploadDir = "src/main/resources/static/images/";

                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);

                Files.write(path, imageFile.getBytes());

                product.setImage("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Nếu không tải file, JPA sẽ tự động lấy giá trị từ ô nhập URL (th:field="*{image}")

        productService.add(product);
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productService.get(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.getAll());
            return "product/add";
        }
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}