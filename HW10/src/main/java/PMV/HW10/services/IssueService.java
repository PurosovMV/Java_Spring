package PMV.HW10.services;

import PMV.HW10.controllers.IssueRequest;
import PMV.HW10.entity.Book;
import PMV.HW10.entity.Issue;
import PMV.HW10.entity.Reader;
import PMV.HW10.exceptions.BookHasBeenReturnedException;
import PMV.HW10.exceptions.MoreThanAllowedBooksException;
import PMV.HW10.repository.BookRepository;
import PMV.HW10.repository.IssueRepository;
import PMV.HW10.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Setter
    @Value("${spring.application.issue.max_allowed-books:3}")
    private int MAX_ALLOWED_BOOKS;

    public void delete(long id){
        Issue issue = issueRepository.findById(id).orElse(null);
        if (issue == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        issueRepository.delete(issue);
    }

    public Issue returnBook(long id) {
        if (issueRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (issueRepository.getReferenceById(id).getReturnedAt() != null) {
            throw new BookHasBeenReturnedException("Книга с id:" +
                    issueRepository.getReferenceById(id).getBook().getId() +
                    " уже была возвращена");
        }

        Issue returnedIssue = issueRepository.getReferenceById(id);
        returnedIssue.setReturnedAt(LocalDate.now());
        issueRepository.save(returnedIssue);
        return returnedIssue;
    }


    public Issue create(IssueRequest issueRequest) {
        Reader reader = readerRepository.findById(issueRequest.getReaderId()).orElse(null);
        Book book = bookRepository.findById(issueRequest.getBookId()).orElse(null);
        if (book == null) {
            log.info("Не удалось найти книгу: " + issueRequest.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу: " + issueRequest.getBookId());
        }
        if (reader == null) {
            log.info("Не удалось найти читателя: " + issueRequest.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя: " + issueRequest.getReaderId());
        }
        if (!checkMaxAllowedBooks(issueRequest.getReaderId())) {
            log.info("У читателя с id={} превышено допустимое значение хранения книг", issueRequest.getReaderId());
            throw new MoreThanAllowedBooksException("Превышено допустимое количество книг, разрешённых к выдаче");
        }

        Issue issue = new Issue(reader, book);
        return issueRepository.save(issue);
    }

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    public Issue findById(long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        ifIssueNull(issue, id);
        return issueRepository.getReferenceById(id);
    }

    private static void ifIssueNull(Issue issue, long id) {
        if (issue == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found issue with id = " + id);
        }
    }

    /**
     * Проверка допустимого количества выданных книг пользователю
     * @param id id пользователя
     * @return
     */
    public boolean checkMaxAllowedBooks(long id) {
        int countOfBooks = 1;
        for (Issue issue : issueRepository.findAll()) {
            if (issue.getReader().getId() == id) {
                countOfBooks++;
                if (countOfBooks > MAX_ALLOWED_BOOKS) {
                    return false;
                }
            }
        }
        return true;
    }

    public Map<String, List<Book>> findBooksByReaderId(long readerId) {
        Map<String, List<Book>> map = new HashMap<>();
        List<Book> books = new ArrayList<>();
//        Reader reader = readerRepository.findById(readerId).orElse(null);
        List<Issue> issues = issueRepository.findAll();
        for(Issue issue: issues){
            if(issue.getReader().getId() == readerId){
                books.add(issue.getBook());
            }
        }
        String reader = readerRepository.getReferenceById(readerId).getFirstName() +
                " " +
                readerRepository.getReferenceById(readerId).getLastName();
        map.put(reader, books);
        return map;
    }

}