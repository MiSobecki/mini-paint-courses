package courses.paint.mini.dto.course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "courseStepId"})
public class CourseStepImageDto {

    private String id;
    private String courseStepId;
    private byte[] imageData;

}
