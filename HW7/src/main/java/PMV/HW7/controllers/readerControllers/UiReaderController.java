package PMV.HW7.controllers.readerControllers;

import PMV.HW7.entity.Reader;
import PMV.HW7.services.ReaderServices;
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
public class UiReaderController {
    private final ReaderServices readerServices;
@Autowired
    public UiReaderController(ReaderServices readerServices) {
        this.readerServices = readerServices;
    }
    @GetMapping("/readers")
    public String getAllReaders (Model model){
        List<Reader> readers = readerServices.getAllReaders();
        model.addAttribute("readers", readers);
        return "allReaders";
    }

}
