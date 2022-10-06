package courses.paint.mini.model.course;

import courses.paint.mini.model.User;
import courses.paint.mini.model.game.Miniature;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
public class Course {

    private String id;
    private String title;
    private Set<CourseStep> steps;
    private Miniature miniature;
    private User user;

}
