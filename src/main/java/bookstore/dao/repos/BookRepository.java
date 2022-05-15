package bookstore.dao.repos;

import bookstore.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getAllByCategory(String category);
    Book getByName(String name);
}
