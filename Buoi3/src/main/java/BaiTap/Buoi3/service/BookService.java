package BaiTap.Buoi3.service;
import BaiTap.Buoi3.model.Book;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public BookService() {
        addBook(new Book(null, "Lập trình Java Core", "Nguyễn Văn A"));
        addBook(new Book(null, "Spring Boot & Thymeleaf", "Huy Cường"));
        addBook(new Book(null, "Cấu trúc dữ liệu", "Trần Thị B"));
        addBook(new Book(null, "Lập trình mạng J2EE", "Lê Văn C"));
        addBook(new Book(null, "Microservices cơ bản", "Nguyễn Văn A"));
    }

    public List<Book> getAllBooks() { return books; }

    public void addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
    }

    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    public void updateBook(Book updatedBook) {
        getBookById(updatedBook.getId()).ifPresent(b -> {
            b.setTitle(updatedBook.getTitle());
            b.setAuthor(updatedBook.getAuthor());
        });
    }

    public void deleteBook(Long id) {
        books.removeIf(b -> b.getId().equals(id));
    }
}