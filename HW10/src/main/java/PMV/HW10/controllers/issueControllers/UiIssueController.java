package PMV.HW10.controllers.issueControllers;

import PMV.HW10.entity.Book;
import PMV.HW10.entity.Issue;
import PMV.HW10.services.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/issue")
@Slf4j
public class UiIssueController {
    private IssueService issueService;

    @Autowired
    public UiIssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping()
    public String findAll(Model model){
        List<Issue> issues = issueService.findAll();
        model.addAttribute("issues", issues);
        return "issues";
    }

    @GetMapping("/reader/{id}")
    public String getBooksFromReaderById(@PathVariable Long id, Model model){
        Map<String, List<Book>> booksByReader = issueService.findBooksByReaderId(id);
        Map.Entry<String, List<Book>> entry = booksByReader.entrySet().iterator().next();
        String reader = entry.getKey();
        List<Book> books = entry.getValue().stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());
        model.addAttribute("reader", reader);
        model.addAttribute("books", books);
        return "booksFromReader";
    }
}