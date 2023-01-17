package courses.paint.mini.mapper.course;

import courses.paint.mini.dto.course.CourseStepImageDto;
import courses.paint.mini.model.course.CourseStep;
import courses.paint.mini.model.course.CourseStepImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CourseStepImageDtoMapper {

    @Mapping(source = "courseStep.id", target = "courseStepId")
    @Mapping(source = "image", target = "imageData")
    CourseStepImageDto fromCourseStepImage(CourseStepImage courseStepImage);

    @Named("fromCourseStepId")
    default CourseStep fromCourseStepId(String id) {
        if (id == null) {
            return null;
        }

        var courseStep = new CourseStep();
        courseStep.setId(id);

        return courseStep;
    }

}
