package courses.paint.mini.adapter;

import courses.paint.mini.mapper.UserMapper;
import courses.paint.mini.model.User;
import courses.paint.mini.port.RequestUserPort;
import courses.paint.mini.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestUserAdapter implements RequestUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> getByUsername(String username) {
        var userEntity = userRepository.findByUsername(username);

        return userEntity.map(userMapper::toUser);
    }

}
