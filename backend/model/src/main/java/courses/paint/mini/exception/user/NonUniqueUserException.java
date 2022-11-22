package courses.paint.mini.exception.user;

import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import lombok.Getter;

@Getter
public class NonUniqueUserException extends BasicException {

    private final ExceptionType exceptionType;

    public NonUniqueUserException(String username) {
        super("User of the username: '" + username + "' already exists");
        exceptionType = ExceptionType.NOT_UNIQUE;
    }
}
