package PMV.HW7.controllers.readerControllers;


import PMV.HW7.controllers.ReaderRequest;
import PMV.HW7.entity.Issue;
import PMV.HW7.entity.Reader;
import PMV.HW7.services.ReaderService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/readers")
public class ReaderController {
    private ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete reader by id")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        readerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}")
    @Operation(summary = "get reader by id")
    public ResponseEntity<Reader> getById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(readerService.getById(id));
    }

    @GetMapping
    @Operation(summary = "get all readers")
    public ResponseEntity<List<Reader>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(readerService.findAll());
    }

    @Operation(summary = "save reader to repository")
    @PostMapping
    public ResponseEntity<Reader> create(@RequestBody ReaderRequest readerRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(readerService.create(readerRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}