package courses.paint.mini.usecase;

import courses.paint.mini.exception.course.NonUniqueCourseException;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCourseUseCase {

    private final RequestCoursePort requestCoursePort;
    private final CommandCoursePort commandCoursePort;

    Course execute(Course course) {
        var existingCourse = requestCoursePort.getByTitleAndUserId(
                course.getTitle(),
                course.getUser().getId());

        if (existingCourse.isPresent()) {
            throw new NonUniqueCourseException(course.getTitle(), course.getId());
        }

        return commandCoursePort.create(course);
    }

}
