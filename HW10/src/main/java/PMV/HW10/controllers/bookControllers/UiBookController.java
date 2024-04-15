package PMV.HW10.controllers.bookControllers;

import PMV.HW10.entity.Book;
import PMV.HW10.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class UiBookController {
    private BookService bookService;

    @Autowired
    public UiBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

}