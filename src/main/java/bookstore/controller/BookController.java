package bookstore.controller;


import bookstore.dao.entity.Book;
import bookstore.service.BasketService;
import bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/shop")
public class BookController {
    private final BookService bookService;
    private final BasketService basketService;

    public BookController(BookService bookService, BasketService basketService) {
        this.bookService = bookService;
        this.basketService = basketService;
    }

    @GetMapping("/{category}")
    @ResponseBody
    public ModelAndView getBooks(@PathVariable String category){
        ModelAndView modelAndView = new ModelAndView("shop");
        List<Book> books = bookService.getAllByCategory(category);
        modelAndView.addObject("book", books);
        return modelAndView;
    }

    @GetMapping("/{category}/get/{name}")
    @ResponseBody
    public ModelAndView getBook(@PathVariable String category, @PathVariable String name){
        List<Book> books = bookService.getAllByCategory(category);
        Book book = bookService.getByName(books, name);
        ModelAndView modelAndView = new ModelAndView("book");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping("{category}/add/{name}")
    @ResponseBody
    public ModelAndView addBook(@PathVariable String category, @PathVariable String name) {
        List<Book> books = bookService.getAllByCategory(category);
        Book book = bookService.getByName(books, name);
        ModelAndView modelAndView = new ModelAndView("shop");
        basketService.addBook(book);
        modelAndView.addObject("book", books);
        modelAndView.addObject("product", book.getName());
        modelAndView.addObject("purchase", true);
        return modelAndView;
    }
}
