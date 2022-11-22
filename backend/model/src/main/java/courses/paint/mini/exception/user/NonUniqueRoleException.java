package courses.paint.mini.exception.user;

import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import lombok.Getter;

@Getter
public class NonUniqueRoleException extends BasicException {

    private final ExceptionType exceptionType;

    public NonUniqueRoleException(String roleName) {
        super("Role of the name: '" + roleName + "' already exists");
        exceptionType = ExceptionType.NOT_UNIQUE;
    }
}
