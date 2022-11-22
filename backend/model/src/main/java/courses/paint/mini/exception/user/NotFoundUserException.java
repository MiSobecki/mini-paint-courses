package courses.paint.mini.exception.user;

import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import lombok.Getter;

@Getter
public class NotFoundUserException extends BasicException {

    private final ExceptionType exceptionType;

    public NotFoundUserException(String username) {
        super("User of username: '" + username + "' does not exist");
        exceptionType = ExceptionType.NOT_FOUND;
    }
}
