package PMV.HW3.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Issue {
    private static long GEN_ID;

    private final long id;
    private final long bookId;
    private final long readerId;
    private LocalDateTime issued_at;
    private LocalDateTime returned_at;

    public Issue(long bookId, long readerId) {
        this.id = GEN_ID++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
    }
}