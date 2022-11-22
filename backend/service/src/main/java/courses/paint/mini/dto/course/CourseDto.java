package courses.paint.mini.dto.course;

import courses.paint.mini.dto.user.UserDto;
import courses.paint.mini.dto.game.CourseMiniatureDto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
public class CourseDto {

    @Null(message = "Course id should be null")
    private String id;
    @NotBlank(message = "Course title is mandatory")
    @Size(max = 100, message = "Course title should has size of max 100 characters")
    private String title;
    @Size(max = 500, message = "Course short description should has size of max 500 characters")
    private String shortDescription;
    @NotEmpty(message = "Course should has at least 1 step")
    @Valid
    private Set<CourseStepDto> steps;
    @NotNull(message = "Miniature is mandatory")
    @Valid
    private CourseMiniatureDto miniature;
    @NotNull(message = "User is mandatory")
    @Valid
    private UserDto user;

}
