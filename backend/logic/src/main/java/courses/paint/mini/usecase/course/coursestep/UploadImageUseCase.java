package courses.paint.mini.usecase.course.coursestep;

import courses.paint.mini.model.course.CourseStep;
import courses.paint.mini.model.course.CourseStepImage;
import courses.paint.mini.port.CommandCourseStepImagePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UploadImageUseCase {

    private final CommandCourseStepImagePort commandCourseStepImagePort;

    public CourseStepImage execute(String courseStepId,
                                   byte[] imageData) {
        var courseStep = new CourseStep();
        courseStep.setId(courseStepId);

        var courseStepImage = new CourseStepImage(null, courseStep, imageData);

        return commandCourseStepImagePort.create(courseStepImage);
    }

}
