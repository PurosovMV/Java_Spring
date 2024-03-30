package PMV.HW3.controllers.issueControllers;


import PMV.HW3.controllers.IssueRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import PMV.HW3.entity.*;
import PMV.HW3.repository.*;
import PMV.HW3.services.*;
import PMV.HW3.exceptions.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/issue")
@Slf4j
public class IssueController {
    private IssueServices issueService;

    @Autowired
    public IssueController(IssueServices issueService) {
        this.issueService = issueService;
    }

    @PutMapping("{id}")
    public ResponseEntity<Issue> returnIssue(@PathVariable long id){
        log.info("Поступил запрос на возврат книги в библиотеку");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(issueService.returnIssue(id));
        } catch (BookHasBeenReturnedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Issue>> getAllIssues(){
        log.info("Поступил запрос на информацию обо всех выдачах");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(issueService.getAllIssues());
        } catch (NullPointerException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId={}, bookId={}",
                issueRequest.getReaderId(),
                issueRequest.getBookId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(issueService.createIssue(issueRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (AllreadyHaveBook | MoreThanAllowedBooksException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable long id){
        log.info("Поступил запрос на информацию о выдаче ");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(issueService.getIssueById(id));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}