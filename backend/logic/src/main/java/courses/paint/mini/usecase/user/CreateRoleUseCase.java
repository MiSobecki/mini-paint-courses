package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NonUniqueRoleException;
import courses.paint.mini.model.Role;
import courses.paint.mini.port.CommandRolePort;
import courses.paint.mini.port.RequestRolePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRoleUseCase {

    private final CommandRolePort commandRolePort;
    private final RequestRolePort requestRolePort;

    public Role execute(Role role) {
        if (requestRolePort.getRoleByNameIgnoreCase(role.getName()).isPresent()) {
            throw new NonUniqueRoleException(role.getName());
        }

        role.setName(role.getName().toUpperCase());

        return commandRolePort.create(role);
    }

}
