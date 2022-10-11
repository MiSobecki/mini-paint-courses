package courses.paint.mini.model.product;

import courses.paint.mini.constraint.PaintType;
import courses.paint.mini.model.Producer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name", "type"})
public class Paint {

    private String id;
    private String name;
    private PaintType type;
    private Producer producer;

}
