package courses.paint.mini.usecase.course.coursestep;

import courses.paint.mini.port.CommandCourseStepImagePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteImageUseCase {

    private final CommandCourseStepImagePort commandCourseStepImagePort;

    public void execute(String id) {
        commandCourseStepImagePort.delete(id);
    }

}
