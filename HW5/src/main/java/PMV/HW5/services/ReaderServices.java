package PMV.HW5.services;



import PMV.HW5.entity.Issue;
import PMV.HW5.entity.Reader;
import PMV.HW5.repository.IssueRepository;
import PMV.HW5.repository.JpaReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import PMV.HW5.entity.*;
import PMV.HW5.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReaderServices {
    private final JpaReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Reader createReader(String firstName, String lastName) {
        Reader reader = new Reader(firstName, lastName);
        readerRepository.save(reader);
        return reader;
    }

    public Reader getReaderById(long id) {
        return readerRepository.getReferenceById(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public void deleteReader(long id) {
        Reader reader = readerRepository.getReferenceById(id);
        readerRepository.delete(reader);
    }


    public List<Issue> getIssuesForReader(long id){
        return issueRepository.getAllIssues()
                .stream()
                .filter(issue -> issue.getReaderId() == id)
                .collect(Collectors.toList());
    }
}
