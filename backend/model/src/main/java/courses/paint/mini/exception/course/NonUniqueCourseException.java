package courses.paint.mini.exception.course;

import courses.paint.mini.constraint.ExceptionType;
import courses.paint.mini.exception.BasicException;
import lombok.Getter;

@Getter
public class NonUniqueCourseException extends BasicException {

    private final ExceptionType exceptionType;

    public NonUniqueCourseException(String courseTitle,
                                    String courseId) {
        super("Course of the title: '" + courseTitle + "' already exists for this user and has id: '" + courseId + "'");
        exceptionType = ExceptionType.NOT_UNIQUE;
    }
}
