package courses.paint.mini.service;

import courses.paint.mini.model.User;
import courses.paint.mini.usecase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final CreateUserUseCase createUserUseCase;

    public User register(User user) {
        var userPassword = user.getPassword();

        user.setPassword(passwordEncoder.encode(userPassword));

        return createUserUseCase.execute(user);
    }

}
