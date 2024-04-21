package PMV.issue_service;

import lombok.Data;

import java.util.UUID;
@Data
public class Book {
   private UUID id;
   private String name;
}
