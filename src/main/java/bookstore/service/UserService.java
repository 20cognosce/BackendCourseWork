package bookstore.service;

import bookstore.dao.entity.User;
import bookstore.dao.entity.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getByLogin(String login);
    void createUser(UserRegistrationDto registration, String cookie);
}
