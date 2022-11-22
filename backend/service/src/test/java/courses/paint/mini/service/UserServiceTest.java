package courses.paint.mini.service;

import courses.paint.mini.model.User;
import courses.paint.mini.usecase.user.CreateUserUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CreateUserUseCase createUserUseCase;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldEncodePasswordAndCreateUserCorrectly() {
        // given
        var user = new User("465346sf", "user", "passwd", new HashSet<>());

        var userPassword = user.getPassword();

        // when
        userService.register(user);

        // then
        verify(passwordEncoder).encode(userPassword);
    }

}