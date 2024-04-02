package PMV.HW7.services;


import PMV.HW7.entity.Book;
import PMV.HW7.repository.JpaBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BookServices {
    private final JpaBookRepository bookRepository;

    public Book createBook(String name) {
        Book book = new Book(name);
        bookRepository.save(book);
        return book;
    }

    public Book getBookById(long id) {
        return bookRepository.getReferenceById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().toList();
    }

    public void deleteBook(long id) {
        Book book = bookRepository.getReferenceById(id);
        bookRepository.delete(book);

    }
}

