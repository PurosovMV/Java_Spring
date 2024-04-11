package PMV.HW7.controllers;


import lombok.Data;

@Data
public class ReaderRequest {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String role;
}
