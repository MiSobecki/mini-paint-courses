package courses.paint.mini.dto.game;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class CourseMiniatureDto {

    private String id;
    private String name;
    private String type;
    private String factionName;

}
