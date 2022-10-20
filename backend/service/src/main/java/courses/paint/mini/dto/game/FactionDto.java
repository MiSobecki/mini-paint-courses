package courses.paint.mini.dto.game;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class FactionDto {

    private String id;
    private String name;
    private Set<CourseMiniatureDto> miniatures;
    private String gameTitle;

}
