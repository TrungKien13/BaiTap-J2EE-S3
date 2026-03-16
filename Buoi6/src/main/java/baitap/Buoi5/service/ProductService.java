package baitap.Buoi5.service;

import baitap.Buoi5.model.Product;
import baitap.Buoi5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() { return productRepository.findAll(); }
    public void add(Product product) { productRepository.save(product); }
    public Product get(int id) { return productRepository.findById(id).orElse(null); }
    public void update(Product product) { productRepository.save(product); }
    public void delete(int id) { productRepository.deleteById(id); }
}