package courses.paint.mini.mapper.course;

import courses.paint.mini.dto.course.CourseDto;
import courses.paint.mini.dto.course.CourseShortInfoDto;
import courses.paint.mini.mapper.UserDtoMapper;
import courses.paint.mini.mapper.game.MiniatureDtoMapper;
import courses.paint.mini.model.course.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        CourseStepDtoMapper.class,
        MiniatureDtoMapper.class,
        UserDtoMapper.class
})
public interface CourseDtoMapper {

    @Mapping(source = "miniature.faction.name", target = "factionName")
    @Mapping(source = "miniature.faction.game.title", target = "gameTitle")
    @Mapping(source = "user.username", target = "username")
    CourseShortInfoDto shortInfoFromCourse(Course course);

    CourseDto fromCourse(Course course);

    Course toCourse(CourseDto courseDto);

}
