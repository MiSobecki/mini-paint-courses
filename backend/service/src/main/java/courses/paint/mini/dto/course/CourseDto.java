package courses.paint.mini.dto.course;

import courses.paint.mini.dto.UserDto;
import courses.paint.mini.dto.game.CourseMiniatureDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
public class CourseDto {

    private String id;
    private String title;
    private String shortDescription;
    private Set<CourseStepDto> steps;
    private CourseMiniatureDto miniature;
    private UserDto user;

}
