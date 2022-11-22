package courses.paint.mini.usecase.user;

import courses.paint.mini.model.User;
import courses.paint.mini.port.CommandUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignRoleUseCase {

    private final CommandUserPort commandUserPort;
    private final GetUserByUsernameUseCase getUserByUsernameUseCase;
    private final GetRoleByIdUseCase getRoleByIdUseCase;

    public User execute(String username,
                        String roleId) {
        var user = getUserByUsernameUseCase.execute(username);

        var role = getRoleByIdUseCase.execute(roleId);

        user.getRoles().add(role);

        return commandUserPort.update(user);
    }

}
