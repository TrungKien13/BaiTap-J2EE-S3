package baitap.Buoi5.component;

import baitap.Buoi5.model.Category;
import baitap.Buoi5.model.Product;
import baitap.Buoi5.repository.CategoryRepository;
import baitap.Buoi5.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void seedData() {
        // 1. Kiểm tra nếu chưa có dữ liệu thì mới nạp
        if (categoryRepository.count() == 0) {
            // Tạo danh mục mẫu
            Category mobile = new Category();
            mobile.setName("Điện thoại");
            categoryRepository.save(mobile);

            Category laptop = new Category();
            laptop.setName("Laptop");
            categoryRepository.save(laptop);

            // 2. Tạo sản phẩm mẫu gắn với danh mục vừa tạo
            if (productRepository.count() == 0) {
                Product p1 = new Product();
                p1.setName("iPhone 15 Pro Max");
                p1.setPrice(32000000);
                p1.setImage("iphone15.jpg");
                p1.setCategory(mobile); // Gán danh mục Điện thoại
                productRepository.save(p1);

                Product p2 = new Product();
                p2.setName("MacBook Pro M3");
                p2.setPrice(45000000);
                p2.setImage("macbook.jpg");
                p2.setCategory(laptop); // Gán danh mục Laptop
                productRepository.save(p2);
            }
        }
    }
}