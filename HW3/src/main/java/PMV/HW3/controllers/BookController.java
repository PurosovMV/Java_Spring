package PMV.HW3.controllers;



import PMV.HW3.services.BookServices;
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

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    private final BookServices bookServices;

    @Autowired
    public BookController(BookServices bookService) {
        this.bookServices = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody String bookName){
        log.info("Поступил запрос на создание книги: " + bookName);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookServices.createBook(bookName));
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        log.info("Поступил запрос на выдачу информации о всей библиотеки");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookServices.getAllBooks());
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable long id){
        log.info("Поступил запрос на выдачу информации о книге с Id: " + id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookServices.getBookById(id));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable long id){
        final boolean deleted = bookServices.deleteBook(id);
        log.info("Поступил запрос на удаление книги с Id: " + id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
