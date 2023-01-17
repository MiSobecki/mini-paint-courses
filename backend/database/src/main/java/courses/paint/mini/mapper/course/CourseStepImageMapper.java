package courses.paint.mini.mapper.course;

import courses.paint.mini.entity.course.CourseStepImageEntity;
import courses.paint.mini.model.course.CourseStepImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseStepImageMapper {

    @Mapping(source = "imageData", target = "image")
    CourseStepImage toCourseStepImage(CourseStepImageEntity courseStepImageEntity);

    @Mapping(source = "image", target = "imageData")
    CourseStepImageEntity fromCourseStepImage(CourseStepImage courseStepImage);

}
