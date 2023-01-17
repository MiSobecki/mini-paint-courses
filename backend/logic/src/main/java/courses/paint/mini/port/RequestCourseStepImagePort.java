package courses.paint.mini.port;

import courses.paint.mini.model.course.CourseStepImage;

import java.util.List;

public interface RequestCourseStepImagePort {

    List<CourseStepImage> download(String courseStepId);

}
