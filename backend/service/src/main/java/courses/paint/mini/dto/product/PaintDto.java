package courses.paint.mini.dto.product;

import courses.paint.mini.constraint.PaintType;
import courses.paint.mini.dto.ProducerDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name", "type"})
public class PaintDto {

    private String id;
    private String name;
    private PaintType type;
    private ProducerDto producer;

}
