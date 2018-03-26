package app.service;

import app.domain.Book;
import app.repo.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public void deleteBook(Book entity) {
        bookRepo.delete(entity);
    }

    public void save(Book entity) {
        bookRepo.saveAndFlush(entity);
    }

}
