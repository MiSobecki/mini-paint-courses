package courses.paint.mini.model.product;

import courses.paint.mini.constraint.PaintType;
import courses.paint.mini.model.Producer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name", "type"})
public class Paint {

    private String id;
    private String name;
    private PaintType type;
    private Producer producer;

}
