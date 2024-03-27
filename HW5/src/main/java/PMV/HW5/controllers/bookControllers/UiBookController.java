package PMV.HW5.controllers.bookControllers;

import PMV.HW5.entity.Book;
import PMV.HW5.services.BookServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
@Slf4j
public class UiBookController {

    private final BookServices bookServices;
    @Autowired
    public UiBookController(BookServices bookServices){
        this.bookServices = bookServices;
    }
@GetMapping("/books")
    public String getAllBooks(Model model){
        List<Book> books = bookServices.getAllBooks();
        model.addAttribute("books", books);
        return "allBooks";
    }
}
