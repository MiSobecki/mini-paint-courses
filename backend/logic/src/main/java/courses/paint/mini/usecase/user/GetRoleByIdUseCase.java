package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NotFoundRoleByIdException;
import courses.paint.mini.model.Role;
import courses.paint.mini.port.RequestRolePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetRoleByIdUseCase {

    private final RequestRolePort requestRolePort;

    public Role execute(String id) {
        return requestRolePort.getRoleById(id).orElseThrow(() -> new NotFoundRoleByIdException(id));
    }

}
