package bookstore.test.services;

import bookstore.dao.entity.User;
import bookstore.dao.entity.UserRegistrationDto;
import bookstore.dao.repos.UserRepository;
import bookstore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    private UserService userService;
    @Captor
    private ArgumentCaptor<User> captor;

    @Autowired
    public void setUserAuthService(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    public void init(){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setLogin("test");
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setTerms(true);
        userService.createUser(userRegistrationDto, "");
        verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        when(userRepository.getByLogin(captured.getLogin())).thenReturn(captured);
    }

    @Test
    public void UserShouldBeRegister(){
        User user = userRepository.getByLogin("test");
        assertThat(user).isNotNull();
        verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        Assertions.assertEquals(user.getLogin(), captured.getLogin());
    }

    @Test
    public void UserShouldBeFound() {
        String username = "test";

        UserDetails userDetails = userService.loadUserByUsername(username);
        Assertions.assertEquals(username, userDetails.getUsername());
    }
}
