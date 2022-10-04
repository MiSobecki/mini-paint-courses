package courses.paint.mini.model.product;

import courses.paint.mini.model.Producer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class ModelingProduct {

    private String id;
    private String name;
    private String category;
    private Producer producer;

}
