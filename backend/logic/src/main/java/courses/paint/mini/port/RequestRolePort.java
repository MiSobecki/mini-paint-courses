package courses.paint.mini.port;

import courses.paint.mini.model.Role;

import java.util.Optional;

public interface RequestRolePort {

    Optional<Role> getRoleByNameIgnoreCase(String name);

    Optional<Role> getRoleById(String id);

}
