package PMV.HW3.entity;

import lombok.Data;

@Data
public class Book {
    private static long GEN_ID;

    private final long id;
    private final String name;

    public Book(String name) {
        this.id = GEN_ID++;
        this.name = name;
    }

}