package reader_service;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("reader")
public class ReaderController {
    private final List<Reader> readers = new ArrayList<>();

    int rndNum;

    @PostConstruct
    public void generateBooks() {
        for (int i = 0; i < 15; i++) {
            Reader reader = new Reader();
            reader.setIdReader(UUID.randomUUID());
            reader.setFirstName("FirstName: " + i);
            reader.setLastName("LastName: " + i);

            readers.add(reader);


        }
    }

    @GetMapping()
    public List<Reader> getAll() {
        return readers;
    }

    @GetMapping("random")
    public Reader getById() {
        Random random = new Random();
        rndNum = random.nextInt(readers.size());

        return readers.get(rndNum);
    }


}
