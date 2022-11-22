package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NotFoundRoleException;
import courses.paint.mini.model.Role;
import courses.paint.mini.port.RequestRolePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetRoleByNameUseCase {

    private final RequestRolePort requestRolePort;

    public Role execute(String name) {
        return requestRolePort.getRoleByNameIgnoreCase(name).orElseThrow(() -> new NotFoundRoleException(name));
    }

}
