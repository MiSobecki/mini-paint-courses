package courses.paint.mini.port;

import courses.paint.mini.model.User;

import java.util.Optional;

public interface RequestUserPort {

    Optional<User> getByUsername(String username);

}
