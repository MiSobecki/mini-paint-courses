package courses.paint.mini.mapper.course;

import courses.paint.mini.entity.course.PaintingTechniqueEntity;
import courses.paint.mini.model.course.PaintingTechnique;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaintingTechniqueMapper {

    PaintingTechnique toPaintingTechnique(PaintingTechniqueEntity paintingTechniqueEntity);

    PaintingTechniqueEntity fromPaintingTechnique(PaintingTechnique paintingTechnique);

}
