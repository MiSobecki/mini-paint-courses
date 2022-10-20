package courses.paint.mini.dto.product;

import courses.paint.mini.dto.ProducerDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class ModelingProductDto {

    private String id;
    private String name;
    private String category;
    private ProducerDto producer;

}
