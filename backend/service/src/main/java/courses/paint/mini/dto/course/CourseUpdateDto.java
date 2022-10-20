package courses.paint.mini.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateDto {

    @NotBlank(message = "Course title is mandatory")
    @Size(max = 100, message = "Course title should has size of max 100 characters")
    private String title;
    @Size(max = 500, message = "Course short description should has size of max 500 characters")
    private String shortDescription;
    @NotEmpty(message = "Course should has at least 1 step")
    @Valid
    private Set<CourseStepDto> steps;

}
