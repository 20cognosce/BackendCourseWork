package bookstore.controller;

import bookstore.dao.entity.Book;
import bookstore.service.BasketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/basket")
    @ResponseBody
    public ModelAndView getBasket() {
        ModelAndView modelAndView = new ModelAndView("basket");
        List<Book> books = basketService.getSetOfBook(basketService.getBooks());
        books.sort(Comparator.comparing(Book::getId));
        modelAndView.addObject("book", books);
        modelAndView.addObject("num", basketService.getCounts(books, basketService.getBooks()));
        modelAndView.addObject("price", basketService.getPrice(basketService.getBooks()));
        modelAndView.addObject("discount", (int) Math.round(basketService.getDiscount(basketService.getBooks())));
        return modelAndView;
    }

    @GetMapping("/basket/delete/{name}")
    public String deleteBasket(@PathVariable String name) {
        basketService.deleteBook(name);
        return "redirect:/basket";
    }

    @GetMapping("/basket/buy")
    public String deleteBasket() {
        basketService.buy();
        return "redirect:/basket";
    }
}
