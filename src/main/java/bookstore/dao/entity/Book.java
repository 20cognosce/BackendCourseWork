package bookstore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "category")
    String category;
    @Column(name = "name")
    String name;
    @Column(name = "image")
    String image;
    @Column(name = "author")
    String author;
    @Column(name = "price")
    int price;
    @Column(name = "description")
    String description;
    @Column(name = "quantity")
    int quantity;

    @ManyToMany(mappedBy = "books")
    private Set<User> baskets = new HashSet<>();
}
