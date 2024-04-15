package PMV.HW10.controllers.readerControllers;

import PMV.HW10.entity.Reader;
import PMV.HW10.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/reader")
public class UiReaderController {
    private ReaderService readerService;

    @Autowired
    public UiReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping()
    public String findAll(Model model){
        List<Reader> readers = readerService.findAll();
        model.addAttribute("readers", readers);
        return "readers";
    }
}