package PMV.issue_service;

import lombok.Data;

import java.util.UUID;

@Data
public class Issue {
    private UUID id;
    private UUID idBook;
    private String BookName;
    private UUID idReader;
    private String FirstName;
    private String LastName;
}
