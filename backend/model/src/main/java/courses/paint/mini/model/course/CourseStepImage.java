package courses.paint.mini.model.course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class CourseStepImage {

    private String id;
    private CourseStep courseStep;
    private byte[] image;

}
