package baitap.Buoi5.service;
import baitap.Buoi5.model.Category;
import baitap.Buoi5.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;

    public List<Category> getAll() { return categoryRepository.findAll(); }
    public void add(Category category) { categoryRepository.save(category); }
    public Category get(int id) { return categoryRepository.findById(id).orElse(null); }
    public void delete(int id) { categoryRepository.deleteById(id); }
}