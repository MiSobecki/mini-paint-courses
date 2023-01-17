package courses.paint.mini.port;

import courses.paint.mini.model.course.CourseStepImage;

public interface CommandCourseStepImagePort {

    CourseStepImage create(CourseStepImage courseStepImage);

    void delete(String id);

}
