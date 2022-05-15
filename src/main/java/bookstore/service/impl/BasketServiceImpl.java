package bookstore.service.impl;

import bookstore.dao.entity.Book;
import bookstore.dao.entity.User;
import bookstore.dao.repos.BookRepository;
import bookstore.dao.repos.UserRepository;
import bookstore.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Autowired
    public BasketServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        User user = getUser();
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            user.addBook(book);
            bookRepository.save(book);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        User user = getUser();
        return user.getBooks();
    }

    public List<Book> getSetOfBook(List<Book> books) {
        Set<Book> set = new HashSet<>(books);
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getCounts(List<Book> bookList1, List<Book> bookList2) {
        List<Integer> numbers = new ArrayList<>();
        bookList1.forEach(book -> numbers.add(Collections.frequency(bookList2, book)));
        List<String> str = new ArrayList<>();

        var ref = new Object() {
            int i = 0;
        };
        bookList1.forEach(book -> {
            str.add(numbers.get(ref.i) + " x " + book.getPrice());
            ref.i++;
        });

        return str;
    }

    @Override
    public Integer getPrice(List<Book> bookList) {
        double price = bookList.stream().mapToDouble(Book::getPrice).sum();
        price -= price * this.getDiscount(bookList) / 100;
        return (int) price;
    }

    @Override
    public Double getDiscount(List<Book> bookList) {
        double discount = 0;
        if (bookList.size() >= 2) {
            discount = 10;
        }
        if (bookList.size() >= 6) {
            discount = 20;
        }

        return discount;
    }

    @Override
    @Transactional
    public void deleteBook(String name) {
        User user = getUser();
        Book book = bookRepository.getByName(name);
        user.removeBook(book);
        book.setQuantity(book.getQuantity()+1);
        userRepository.save(user);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void buy() {
        User user = getUser();
        List<Book> books = user.getBooks();
        for (Book book : books) {
            Set<User> users = book.getBaskets();
            users.remove(user);
            book.setBaskets(users);
            bookRepository.save(book);
        }
        books.clear();
        user.setBooks(books);
        userRepository.save(user);
    }

    @Override
    public User getUser() {
        String username;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        } catch (NullPointerException e){
            username = "test";
        }

        return userRepository.getByLogin(username);
    }
}
