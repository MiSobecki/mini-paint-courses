package courses.paint.mini.exception.user;

import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import lombok.Getter;

@Getter
public class NotFoundRoleException extends BasicException {

    private final ExceptionType exceptionType;

    public NotFoundRoleException(String name) {
        super("Role of name: '" + name + "' does not exist");
        exceptionType = ExceptionType.NOT_FOUND;
    }
}
