package bookstore.controller;

import bookstore.dao.entity.User;
import bookstore.dao.entity.UserRegistrationDto;
import bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result, @CookieValue("JSESSIONID") String cookie) {

        User existing = userService.getByLogin(userDto.getLogin());
        if (existing != null){
            result.rejectValue("login", "", "Этот логин занят");
        }

        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "registration";
        }

        userService.createUser(userDto, cookie);
        return "redirect:/registration?success";
    }
}
