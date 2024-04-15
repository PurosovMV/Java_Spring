package PMV.HW10.controllers.bookControllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import PMV.HW10.entity.Book;
import PMV.HW10.services.BookService;

import java.util.List;
import java.util.Objects;



    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
    @AutoConfigureWebTestClient
    @ActiveProfiles("test")
    class BookControllerTest {
        @Autowired
        private WebTestClient webTestClient;

        @Autowired
        BookService bookService;

        @Test
        void delete() {
            String bookName = "Евгений Онегин";
            bookService.createBook(bookName);
            long id = bookService.findAll().stream().filter(b -> b.getTitle().equals(bookName)).findFirst().get().getId();
//        bookService.delete(id);

            Book deleteBook = webTestClient.delete()
                    .uri("books/" + id)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(Book.class)
                    .returnResult()
                    .getResponseBody();

            assertNull(deleteBook);
        }

        @Test
        void testSave() {
            String name = "Евгений Онегин";

            Book savedBook = webTestClient.post()
                    .uri("/books")
                    .bodyValue(name)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(Book.class)
                    .returnResult()
                    .getResponseBody();

            assertEquals(name, savedBook.getTitle());

        }

        @Test
        void testGetById() {
            String name = "test";
            bookService.createBook(name);
            long id = bookService.findByTitle(name).getId();
            Book findedBook = webTestClient.get()
                    .uri("books/" + id)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(Book.class)
                    .returnResult()
                    .getResponseBody();

            assertNotNull(findedBook);
            assertEquals(id, findedBook.getId());
            assertEquals(name, findedBook.getTitle());
        }

        @Test
        void testFindAll() {
            bookService.createBook("Обломов");
            bookService.createBook("Кому на Руси жить хорошо");
            List<Book> bookList = bookService.findAll();

            List<Book> responseBody = webTestClient.get()
                    .uri("books")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(new ParameterizedTypeReference<List<Book>>() {
                    })
                    .returnResult()
                    .getResponseBody();

            assertEquals(bookList.size(), responseBody.size());
            for (Book book : responseBody) {
                boolean found = bookList
                        .stream()
                        .filter(it -> Objects.equals(book.getId(), it.getId()))
                        .anyMatch(it -> Objects.equals(book.getTitle(), it.getTitle()));

                assertTrue(found);
            }
        }
    }