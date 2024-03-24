package PMV.HW3.controllers.issueControllers;

import PMV.HW3.entity.Book;
import PMV.HW3.entity.Issue;
import PMV.HW3.entity.Reader;
import PMV.HW3.services.BookServices;
import PMV.HW3.services.IssueServices;
import PMV.HW3.services.ReaderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
@Slf4j
public class UiIssueController {
    private final IssueServices issueServices;
    private final ReaderServices readerServices;
    private final BookServices bookServices;

    @Autowired
    public UiIssueController(IssueServices issueServices, ReaderServices readerServices, BookServices bookServices) {
        this.issueServices = issueServices;
        this.readerServices = readerServices;
        this.bookServices = bookServices;
    }

    @GetMapping("/issues")
    public String getAllIssues(Model model) {
        List<Issue> issues = issueServices.getAllIssues();
        model.addAttribute("issues", issues);
        return "allIssues";
    }

    @GetMapping("/reader/{id}")
    public String getAllBookByReaderId(@PathVariable Long id, Model model) {
        Reader reader = readerServices.getReaderById(id);
        List<Book> books = issueServices.booksThatReaderHas(reader);
        model.addAttribute("reader", reader);
        model.addAttribute("books", books);
        return "booksFromReader";
    }
}
