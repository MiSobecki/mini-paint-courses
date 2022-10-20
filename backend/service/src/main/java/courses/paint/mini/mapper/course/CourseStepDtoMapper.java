package courses.paint.mini.mapper.course;

import courses.paint.mini.dto.course.CourseStepDto;
import courses.paint.mini.model.course.CourseStep;
import courses.paint.mini.model.course.PaintingTechnique;
import courses.paint.mini.model.product.ModelingProduct;
import courses.paint.mini.model.product.Paint;
import org.mapstruct.*;

import java.util.Map;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CourseStepDtoMapper {

    @Mapping(source = "paintTechniqueIdToPaintIdMap", target = "usedPaints")
    @Mapping(source = "usedOtherModelingProductIds", target = "usedOtherModelingProducts")
    CourseStep toCourseStep(CourseStepDto courseStepDto);

    @Mapping(source = "usedPaints", target = "paintTechniqueIdToPaintIdMap")
    @Mapping(source = "usedOtherModelingProducts", target = "usedOtherModelingProductIds")
    CourseStepDto fromCourseStep(CourseStep courseStep);

    @MapMapping(keyQualifiedByName = "fromPaintId", valueQualifiedByName = "fromPaintingTechniqueId")
    Map<Paint, PaintingTechnique> mapPaintingFromIds(Map<String, String> ids);

    @MapMapping(keyQualifiedByName = "toPaintId", valueQualifiedByName = "toPaintingTechniqueId")
    Map<String, String> mapPaintingToIds(Map<Paint, PaintingTechnique> paintingTechniqueMap);

    @IterableMapping(qualifiedByName = "fromModelingProductId")
    Set<ModelingProduct> fromModelingProductIds(Set<String> modelingProductIds);

    @IterableMapping(qualifiedByName = "toModelingProductId")
    Set<String> toModelingProductIds(Set<ModelingProduct> modelingProducts);

    @Named("fromPaintId")
    default Paint fromPaintId(String id) {
        if (id == null) {
            return null;
        }

        var paint = new Paint();
        paint.setId(id);

        return paint;
    }

    @Named("fromPaintingTechniqueId")
    default PaintingTechnique fromPaintingTechniqueId(String id) {
        if (id == null) {
            return null;
        }

        var paintingTechnique = new PaintingTechnique();
        paintingTechnique.setId(id);

        return paintingTechnique;
    }

    @Named("fromModelingProductId")
    default ModelingProduct fromModelingProductId(String id) {
        if (id == null) {
            return null;
        }

        var modelingProduct = new ModelingProduct();
        modelingProduct.setId(id);

        return modelingProduct;
    }

    @Named("toPaintId")
    default String toPaintId(Paint paint) {
        return paint.getId();
    }

    @Named("toPaintingTechniqueId")
    default String toPaintingTechniqueId(PaintingTechnique paintingTechnique) {
        return paintingTechnique.getId();
    }

    @Named("toModelingProductId")
    default String toModelingProductId(ModelingProduct modelingProduct) {
        return modelingProduct.getId();
    }

}
