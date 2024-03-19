package PMV.HW3.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import PMV.HW3.entity.*;
import PMV.HW3.repository.*;


import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BookServices {
    private final BookRepository bookRepository;

    public Book createBook(String name) {
        Book book = new Book(name);
        bookRepository.addBook(book);
        return book;
    }

    public Book getBookById(long id) {
        return bookRepository.getById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public boolean deleteBook(long id) {
        Book book = bookRepository.getById(id);
        return bookRepository.deleteBook(book);

    }
}

