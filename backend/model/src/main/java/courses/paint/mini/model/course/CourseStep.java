package courses.paint.mini.model.course;

import courses.paint.mini.model.product.ModelingProduct;
import courses.paint.mini.model.product.Paint;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "orderNumber"})
public class CourseStep {

    private String id;
    private Long orderNumber;
    private String title;
    private String description;
    private Map<Paint, PaintingTechnique> usedPaints;
    private Set<ModelingProduct> usedOtherModelingProducts;

}
