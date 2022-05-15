package bookstore.test.services;

import bookstore.dao.entity.Book;
import bookstore.dao.entity.User;
import bookstore.dao.repos.BookRepository;
import bookstore.dao.repos.UserRepository;
import bookstore.service.impl.BasketServiceImpl;
import bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private UserRepository userRepository;
    @Captor
    private ArgumentCaptor<Book> captor;
    private BookServiceImpl bookService;
    private BasketServiceImpl basketService;
    private final List<Book> books = new ArrayList<>();

    @Autowired
    public void setBookService(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBasketService(BasketServiceImpl basketService) {
        this.basketService = basketService;
    }

    @BeforeEach
    public void init(){
        User user = new User();
        user.setLogin("test");
        user.setPassword("password");
        user.setId(1L);
        user.setBooks(new ArrayList<>());

        when(userRepository.getByLogin(user.getLogin())).thenReturn(user);
    }

    @Test
    public void getByCategory() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Книга1");
        book1.setQuantity(5);
        book1.setPrice(158);
        book1.setCategory("prose");
        book1.setAuthor("Толстой");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Книга2");
        book2.setQuantity(5);
        book2.setPrice(158);
        book2.setCategory("prose");
        book2.setAuthor("Булгаков");

        basketService.addBook(book1);
        basketService.addBook(book2);
        books.add(book1);
        books.add(book2);
        when(bookRepository.getAllByCategory("prose")).thenReturn(books);

        List<Book> fetched = bookService.getAllByCategory("prose");

        verify(bookRepository, times(1)).getAllByCategory("prose");
        Assertions.assertEquals(books.size(), fetched.size());
    }

    @Test
    public void getByName(){
        Book book = new Book();
        book.setId(1L);
        book.setName("Книга1");
        book.setQuantity(5);
        book.setPrice(158);
        book.setCategory("detective");
        book.setAuthor("Unknown");
        basketService.addBook(book);

        verify(bookRepository).save(captor.capture());
        Book captured = captor.getValue();

        Assertions.assertEquals(bookService
                .getByName(basketService.getBooks(), captured.getName()).getName(), captured.getName());
    }
}
