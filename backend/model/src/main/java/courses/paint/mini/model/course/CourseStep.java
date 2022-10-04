package courses.paint.mini.model.course;

import courses.paint.mini.model.product.ModelingProduct;
import courses.paint.mini.model.product.Paint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Getter
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
