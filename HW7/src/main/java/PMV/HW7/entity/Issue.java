package PMV.HW7.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "issue")
@Data
public class Issue {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "reader_id")
    private long readerId;

    @Column(name = "issued_at")
    private LocalDateTime issued_at;

    @Column(name = "returned_at")
    private LocalDateTime returned_at;

    public Issue() {
    }

    public Issue(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
    }
}