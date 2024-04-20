package PMV.HW3.services;

import PMV.HW3.controllers.*;
import PMV.HW3.entity.Issue;
import PMV.HW3.entity.Reader;
import PMV.HW3.exceptions.BookHasBeenReturnedException;
import PMV.HW3.exceptions.MoreThanAllowedBooksException;
import PMV.HW3.repository.BookRepository;
import PMV.HW3.repository.IssueRepository;
import PMV.HW3.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import PMV.HW3.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Slf4j
@Service
@PropertySource("classpath:application.yml")
public class IssueServices {
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    @Setter
    @Value("${application.issue.max-allowed-books:10}")
    private int max_allowed_books;



    public List<Book> booksThatReaderHas(Reader reader){
        List<Book> books = new ArrayList<>();
        for (Issue issue : issueRepository.getAllIssues()){
            if (issue.getReaderId() == reader.getId()){
                books.add(bookRepository.getAllBooks().get((int) issue.getBookId()));
            }
        }
        return books;
    }

    //Возврат книги в библиотеку
    public Issue returnIssue(long id) {
        if (issueRepository.getById(id).getReturned_at() == null) {
            return issueRepository.returnIssue(id);
        }
        throw new BookHasBeenReturnedException("Книга с id:" +
                issueRepository.getById(id).getBookId() +
                " уже была возвращена");
    }

    public Issue createIssue(IssueRequest issueRequest) {
        if (bookRepository.getById(issueRequest.getBookId()) == null) {
            log.info("Книга не найдена: " + issueRequest.getBookId());
            throw new NoSuchElementException("Книга не найдена: " + issueRequest.getBookId());
        }
        if (readerRepository.getById(issueRequest.getReaderId()) == null) {
            log.info("Читатель не найден: " + issueRequest.getReaderId());
            throw new NoSuchElementException("Читатель не найден: " + issueRequest.getReaderId());
        }
        if (!check(issueRequest.getReaderId())) {
            log.info("У читателя с id={} превышено допустимое значение хранения книг", issueRequest.getReaderId());
            throw new MoreThanAllowedBooksException("Превышено допустимое количество книг, разрешённых к выдаче");
        }


        Issue issue = new Issue(issueRequest.getBookId(), issueRequest.getReaderId());
        issueRepository.createIssue(issue);
        return issue;
    }

    public Issue getIssueById(long id) {
        return issueRepository.getById(id);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }


    //Проверка количества книг у читателя
    public boolean check(long id) {
        int countOfBooks = 1;
        for (Issue issue : issueRepository.getAllIssues()) {
            if (issue.getReaderId() == id) {
                countOfBooks++;
                if (countOfBooks > max_allowed_books) {
                    return false;
                }
            }
        }
        return true;
    }


}
