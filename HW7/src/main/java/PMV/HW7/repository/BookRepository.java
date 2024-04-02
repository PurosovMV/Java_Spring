package PMV.HW7.repository;

import PMV.HW7.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class BookRepository {
    List<Book> books = new ArrayList<>();

    {
        books.add(new Book("Война и Мир"));
        books.add(new Book("Мастер и Маргарита"));
        books.add(new Book("Евгений Онегин"));
        books.add(new Book("Кому на Руси жить хорошо"));
        books.add(new Book("Евгений Онегин"));
    }


    public Book getById(long id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public boolean deleteBook(Book book) {
        return books.removeIf(b -> b.equals(book));
    }
}
