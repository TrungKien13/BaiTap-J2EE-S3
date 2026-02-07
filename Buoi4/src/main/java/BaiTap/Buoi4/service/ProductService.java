package BaiTap.Buoi4.service;

import BaiTap.Buoi4.model.Category;
import BaiTap.Buoi4.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class ProductService {
    private List<Product> listProduct = new ArrayList<>();

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void initData() {
        Category mobile = categoryService.get(1);
        Category laptop = categoryService.get(2);

        listProduct.add(new Product(1, "iPhone 15 Pro Max", "iphone15.jpg", 32000000, mobile));
        listProduct.add(new Product(2, "MacBook Pro M3", "macbook.jpg", 45000000, laptop));
        listProduct.add(new Product(3, "Samsung Galaxy S24", "s24.jpg", 28000000, mobile));
    }

    public List<Product> getAll() { return listProduct; }

    public Product get(int id) {
        return listProduct.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Product newProduct) {
        int maxId = listProduct.stream().mapToInt(Product::getId).max().orElse(0);
        newProduct.setId(maxId + 1);
        listProduct.add(newProduct);
    }

    public void update(Product editProduct) {
        Product find = get(editProduct.getId());
        if (find != null) {
            find.setName(editProduct.getName());
            find.setPrice(editProduct.getPrice());
            find.setCategory(editProduct.getCategory());
            if (editProduct.getImage() != null) {
                find.setImage(editProduct.getImage());
            }
        }
    }

    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        if (!imageProduct.isEmpty()) {
            try {
                // Đường dẫn lưu file thực tế trong project
                Path dirImages = Paths.get("src/main/resources/static/images");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }

                String newFileName = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
                Path pathFileUpload = dirImages.resolve(newFileName);

                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        listProduct.removeIf(p -> p.getId() == id);
    }
}