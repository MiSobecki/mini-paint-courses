package courses.paint.mini.port;

import courses.paint.mini.model.User;

public interface CommandUserPort {

    User create(User user);

    User update(User user);

}
