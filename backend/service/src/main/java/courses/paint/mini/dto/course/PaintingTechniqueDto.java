package courses.paint.mini.dto.course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class PaintingTechniqueDto {

    private String id;
    private String name;

}
