package courses.paint.mini.model.course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class PaintingTechnique {

    private String id;
    private String name;

}
