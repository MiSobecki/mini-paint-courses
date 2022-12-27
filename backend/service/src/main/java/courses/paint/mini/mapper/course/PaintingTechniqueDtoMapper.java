package courses.paint.mini.mapper.course;

import courses.paint.mini.dto.course.PaintingTechniqueDto;
import courses.paint.mini.model.course.PaintingTechnique;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaintingTechniqueDtoMapper {

    PaintingTechniqueDto fromPaintingTechnique(PaintingTechnique paintingTechnique);

}
