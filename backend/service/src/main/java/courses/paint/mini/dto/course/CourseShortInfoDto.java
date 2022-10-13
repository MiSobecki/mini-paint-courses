package courses.paint.mini.dto.course;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
public class CourseShortInfoDto {

    private String id;
    private String title;
    private String shortDescription;
    private String miniatureName;
    private String factionName;
    private String gameTitle;
    private String username;

}
