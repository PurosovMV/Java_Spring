package PMV.HW7.services;


import PMV.HW7.entity.Book;
import PMV.HW7.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Book createBook(String name){
        Book book = new Book(name);
        repository.save(book);
        return book;
    }
    public Book getById(long id){
        return repository.getReferenceById(id);
    }

    public List<Book> findAll(){
        return repository.findAll();
    }

    public void delete(long id){
        Book book = repository.findById(id).orElse(null);
        if (book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no book with id " + id);
        }
        repository.delete(book);
    }
}