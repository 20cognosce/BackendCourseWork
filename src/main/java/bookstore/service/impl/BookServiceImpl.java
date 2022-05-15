package bookstore.service.impl;

import bookstore.dao.entity.Book;
import bookstore.dao.repos.BookRepository;
import bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllByCategory(String category) {
        List<Book> books = bookRepository.getAllByCategory(category);
        books.sort(Comparator.comparing(Book::getId));
        return books;
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByName(List<Book> books, String name) {
        for (Book book : books) {
            if (book.getName().equals(name))
                return book;
        }
        return null;
    }
}
