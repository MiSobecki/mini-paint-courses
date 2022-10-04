package courses.paint.mini.model.course;

import courses.paint.mini.model.User;
import courses.paint.mini.model.game.Miniature;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
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
