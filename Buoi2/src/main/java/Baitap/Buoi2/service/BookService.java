package Baitap.Buoi2.service;

import Baitap.Buoi2.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    public List<Book> getAllBooks() {
        return books;
    }
    public  Book getBookById(int id){
        return  books.stream().filter(book -> book.getId() == id ).findFirst().orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int id, Book updatedBook) {
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                }); // [cite: 303 - 311]
    }

    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id); // [cite: 314 - 316]
    }
}
