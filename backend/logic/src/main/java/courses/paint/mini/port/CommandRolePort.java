package courses.paint.mini.port;

import courses.paint.mini.model.Role;

public interface CommandRolePort {

    Role create(Role role);

    Role update(Role role);

}
