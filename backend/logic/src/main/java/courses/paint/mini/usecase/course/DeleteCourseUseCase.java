package courses.paint.mini.usecase.course;

import courses.paint.mini.exception.course.NonExistingCourseException;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCourseUseCase {

    private final RequestCoursePort requestCoursePort;
    private final CommandCoursePort commandCoursePort;

    public void execute(String id) {
        requestCoursePort.getById(id).orElseThrow(() -> new NonExistingCourseException(id));

        commandCoursePort.delete(id);
    }

}
