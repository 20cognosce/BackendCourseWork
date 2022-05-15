package bookstore.service;

import bookstore.dao.entity.Book;
import bookstore.dao.entity.User;

import java.util.List;

public interface BasketService {
    void addBook(Book book);
    List<Book> getBooks();
    List<Book> getSetOfBook(List<Book> books);
    List<String> getCounts(List<Book> bookList1, List<Book> bookList2);
    Integer getPrice(List<Book> bookList);
    Double getDiscount(List<Book> bookList);
    void deleteBook(String name);
    void buy();
    User getUser();
}
