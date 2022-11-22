package courses.paint.mini.usecase.course;

import courses.paint.mini.port.CommandCoursePort;
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
