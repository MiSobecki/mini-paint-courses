package courses.paint.mini.usecase.course.coursestep;

import courses.paint.mini.model.course.CourseStepImage;
import courses.paint.mini.port.RequestCourseStepImagePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DownloadImagesUseCase {

    private final RequestCourseStepImagePort requestCourseStepImagePort;

    public List<CourseStepImage> execute(String courseStepId) {
        return requestCourseStepImagePort.download(courseStepId);
    }

}
