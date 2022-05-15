package bookstore.test.services;

import bookstore.dao.entity.Book;
import bookstore.dao.entity.User;
import bookstore.dao.repos.BookRepository;
import bookstore.dao.repos.UserRepository;
import bookstore.service.impl.BasketServiceImpl;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BasketServiceImplTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BookRepository bookRepository;
    @Captor
    private ArgumentCaptor<Book> captor;
    private BasketServiceImpl basketService;

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
    public void getBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Книга1");
        book1.setQuantity(5);
        book1.setPrice(158);
        book1.setCategory("prose");
        book1.setAuthor("Пушкин");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Книга2");
        book2.setQuantity(5);
        book2.setPrice(158);
        book2.setCategory("prose");
        book2.setAuthor("Лермонтов");

        basketService.addBook(book1);
        basketService.addBook(book2);

        List<Book> fetched = basketService.getBooks();
        User user = userRepository.getByLogin("test");
        Assertions.assertEquals(user.getBooks(), fetched);
    }

    @Test
    public void addBook(){
        Book book = new Book();
        book.setId(1L);
        book.setName("Книга1");
        book.setQuantity(5);
        book.setPrice(158);
        book.setCategory("detective");
        book.setAuthor("Arthur Conan Doyle");

        basketService.addBook(book);

        verify(bookRepository).save(captor.capture());
        Book captured = captor.getValue();
        Assertions.assertEquals(book.getId(), captured.getId());
        Assertions.assertEquals(book.getName(), captured.getName());
        Assertions.assertEquals(book.getQuantity(), captured.getQuantity());
        Assertions.assertEquals(book.getPrice(), captured.getPrice());
        Assertions.assertEquals(book.getCategory(), captured.getCategory());
        Assertions.assertEquals(book.getAuthor(), captured.getAuthor());

        User user = userRepository.getByLogin("test");
        Assertions.assertNotEquals(user.getBooks().indexOf(book),-1);
    }

    @Test
    public void deleteBook(){
        Book book = new Book();
        book.setId(1L);
        book.setName("Книга1");
        book.setQuantity(5);
        book.setPrice(158);
        book.setCategory("fantasy");
        book.setAuthor("Author1");

        basketService.addBook(book);

        verify(bookRepository).save(captor.capture());
        Book captured = captor.getValue();

        when(bookRepository.getByName(book.getName())).thenReturn(book);
        basketService.deleteBook(book.getName());

        Assertions.assertEquals(basketService.getBooks().indexOf(captured), -1);
    }

    @Test
    public void buy(){
        Book book = new Book();
        book.setId(1L);
        book.setName("Книга1");
        book.setQuantity(5);
        book.setPrice(158);
        book.setCategory("non-fiction");
        book.setAuthor("Издательство МИФ");

        basketService.addBook(book);

        verify(bookRepository).save(captor.capture());
        Book captured = captor.getValue();

        basketService.buy();

        User user = userRepository.getByLogin("test");

        Assertions.assertEquals(basketService.getBooks().indexOf(captured), -1);
        Assertions.assertFalse(book.getBaskets().contains(user));
    }
}
