package bookstore.service;

import bookstore.dao.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllByCategory(String category);
    Book getByName(List<Book> bookList, String name);
}
