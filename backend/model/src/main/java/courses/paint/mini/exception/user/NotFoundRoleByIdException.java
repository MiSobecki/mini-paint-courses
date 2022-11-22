package courses.paint.mini.exception.user;

import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import lombok.Getter;

@Getter
public class NotFoundRoleByIdException extends BasicException {

    private final ExceptionType exceptionType;

    public NotFoundRoleByIdException(String id) {
        super("Role of id: '" + id + "' does not exist");
        exceptionType = ExceptionType.NOT_FOUND;
    }
}
