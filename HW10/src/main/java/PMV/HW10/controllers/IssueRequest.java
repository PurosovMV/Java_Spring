package PMV.HW10.controllers;


import lombok.Data;

@Data
public class IssueRequest {
    private final long readerId;
    private final long bookId;

}
