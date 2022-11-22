package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NonUniqueUserException;
import courses.paint.mini.model.User;
import courses.paint.mini.port.CommandUserPort;
import courses.paint.mini.port.RequestUserPort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CreateUserUseCase {

    private final static String USER_ROLE_NAME = "USER";

    private final CommandUserPort commandUserPort;
    private final GetRoleByNameUseCase getRoleByNameUseCase;
    private final RequestUserPort requestUserPort;

    public User execute(User user) {
        if (requestUserPort.getByUsername(user.getUsername()).isPresent()) {
            throw new NonUniqueUserException(user.getUsername());
        }

        var userRole = getRoleByNameUseCase.execute(USER_ROLE_NAME);
        user.setRoles(Set.of(userRole));

        return commandUserPort.create(user);
    }

}
