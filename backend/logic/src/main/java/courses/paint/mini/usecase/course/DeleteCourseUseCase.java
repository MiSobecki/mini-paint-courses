package courses.paint.mini.usecase.course;

import courses.paint.mini.exception.course.NonExistingCourseException;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCourseUseCase {

    private final GetCourseByIdUseCase getCourseByIdUseCase;
    private final CommandCoursePort commandCoursePort;

    public void execute(String id) {
        getCourseByIdUseCase.execute(id);

        commandCoursePort.delete(id);
    }

}
