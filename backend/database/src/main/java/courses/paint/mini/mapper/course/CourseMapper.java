package courses.paint.mini.mapper.course;

import courses.paint.mini.entity.course.CourseEntity;
import courses.paint.mini.mapper.UserMapper;
import courses.paint.mini.mapper.game.MiniatureMapper;
import courses.paint.mini.model.course.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CourseStepMapper.class, MiniatureMapper.class, UserMapper.class})
public interface CourseMapper {

    @Mapping(source = "miniature", target = "miniature", qualifiedByName = "toMiniature")
    Course toCourse(CourseEntity courseEntity);

    @Mapping(source = "miniature", target = "miniature", qualifiedByName = "fromMiniature")
    CourseEntity fromCourse(Course course);

}
