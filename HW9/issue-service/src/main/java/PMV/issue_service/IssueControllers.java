package PMV.issue_service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class IssueControllers {
    private List<Issue> list = new ArrayList<>();
    private final BookProvider provider;
    private final ReaderProvider readerProvider;

    @PostConstruct
    public void generateIssue() {
        for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            issue.setIdReader(readerProvider.getRandomReaderId());
            issue.setFirstName(readerProvider.getRandomReaderFirstName());
            issue.setLastName(readerProvider.getRandomReaderLastName());
            issue.setIdBook(provider.getRandomBookId());
            issue.setBookName(provider.getRandomBookName());
            list.add(issue);

        }
    }

    @GetMapping("refresh")
    public List<Issue> refresh() {
        generateIssue();
        return list;
    }


    @GetMapping("issue")
    public List<Issue> getAll() {
        return list;
    }


}
