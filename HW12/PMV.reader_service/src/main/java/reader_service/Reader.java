package reader_service;

import lombok.Data;

import java.util.UUID;

@Data
public class Reader {

    private UUID idReader;
    private String FirstName;
    private String LastName;


}
