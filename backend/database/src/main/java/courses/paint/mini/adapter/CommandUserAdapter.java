package courses.paint.mini.adapter;

import courses.paint.mini.mapper.UserMapper;
import courses.paint.mini.model.User;
import courses.paint.mini.port.CommandUserPort;
import courses.paint.mini.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandUserAdapter implements CommandUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        var userEntity = userMapper.fromUser(user);
        userEntity = userRepository.save(userEntity);

        return userMapper.toUser(userEntity);
    }

    @Override
    public User update(User user) {
        var userEntity = userMapper.fromUser(user);
        userEntity = userRepository.save(userEntity);

        return userMapper.toUser(userEntity);
    }

}
