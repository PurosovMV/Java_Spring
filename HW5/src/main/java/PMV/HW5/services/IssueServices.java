package PMV.HW5.services;

import PMV.HW5.controllers.IssueRequest;
import PMV.HW5.entity.Book;
import PMV.HW5.entity.Issue;
import PMV.HW5.entity.Reader;
import PMV.HW5.exceptions.BookHasBeenReturnedException;
import PMV.HW5.exceptions.MoreThanAllowedBooksException;
import PMV.HW5.repository.JpaBookRepository;
import PMV.HW5.repository.JpaIssueRepository;
import PMV.HW5.repository.JpaReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Slf4j
@Service
@PropertySource("classpath:application.yaml")
public class IssueServices {
    private final JpaBookRepository bookRepository;
    private final JpaIssueRepository issueRepository;
    private final JpaReaderRepository readerRepository;

    @Setter
    @Value("${spring.application.issue.max_allowed_books:1}")
    private int max_allowed_books;

    public Reader getReader(long id) {
        return readerRepository.findAll().stream().filter(reader -> reader.getId() == id).findFirst().orElse(null);
    }


    public List<Book> booksThatReaderHas(Reader reader) {
        List<Book> books = new ArrayList<>();
        for (Issue issue : issueRepository.findAll()) {
            if (issue.getReaderId() == reader.getId()) {
                books.add(bookRepository.findAll().get((int) issue.getBookId()));
            }
        }
        return books;
    }


    public Issue returnIssue(long id) {
        if (issueRepository.getReferenceById(id).getReturned_at() == null) {
            return issueRepository.getReferenceById(id);
        }
        throw new BookHasBeenReturnedException("Книга с id:" +
                issueRepository.getReferenceById(id).getBookId() +
                " уже была возвращена");
    }

    public Issue createIssue(IssueRequest issueRequest) {
        if (bookRepository.getReferenceById(issueRequest.getBookId()) == null) {
            log.info("Не удалось найти книгу: " + issueRequest.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу: " + issueRequest.getBookId());
        }
        readerRepository.getReferenceById(issueRequest.getReaderId());
        if (!check(issueRequest.getReaderId())) {
            log.info("У читателя с id={} превышено допустимое значение хранения книг", issueRequest.getReaderId());
            throw new MoreThanAllowedBooksException("Превышено допустимое количество книг, разрешённых к выдаче");
        }


        Issue issue = new Issue(issueRequest.getBookId(), issueRequest.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue getIssueById(long id) {
        return issueRepository.getReferenceById(id);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }


    public boolean check(long id) {
        int countOfBooks = 1;
        for (Issue issue : issueRepository.findAll()) {
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