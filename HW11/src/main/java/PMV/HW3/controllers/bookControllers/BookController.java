package PMV.HW3.controllers.bookControllers;


import PMV.HW3.services.BookServices;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import PMV.HW3.entity.*;
import PMV.HW3.repository.*;
import PMV.HW3.services.*;
import PMV.HW3.exceptions.*;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    private final BookServices bookService;


    @Autowired
    public BookController(BookServices bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody String bookName) {
        log.info("Поступил запрос на создание книги: " + bookName);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookName));
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Поступил запрос на выдачу информации о всей библиотеке");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable long id) {
        log.info("Поступил запрос на выдачу информации о книге с Id: " + id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable long id) {
        log.info("Поступил запрос на удаление книги с Id: " + id);

        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}