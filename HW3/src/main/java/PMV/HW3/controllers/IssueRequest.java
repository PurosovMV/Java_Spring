package PMV.HW3.controllers;


import lombok.Data;

@Data
public class IssueRequest {
    private final long readerId;
    private final long bookId;

}
