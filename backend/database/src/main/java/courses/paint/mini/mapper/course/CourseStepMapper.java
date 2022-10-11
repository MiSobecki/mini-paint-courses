package courses.paint.mini.mapper.course;

import courses.paint.mini.entity.course.CourseStepEntity;
import courses.paint.mini.entity.course.PaintingTechniqueEntity;
import courses.paint.mini.entity.product.PaintEntity;
import courses.paint.mini.mapper.product.ModelingProductMapper;
import courses.paint.mini.mapper.product.PaintMapper;
import courses.paint.mini.model.course.CourseStep;
import courses.paint.mini.model.course.PaintingTechnique;
import courses.paint.mini.model.product.Paint;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring", uses = {ModelingProductMapper.class, PaintMapper.class, PaintingTechniqueMapper.class})
public interface CourseStepMapper {

    CourseStep toCourseStep(CourseStepEntity courseStepEntity);

    CourseStepEntity fromCourseStep(CourseStep courseStep);

    Map<Paint, PaintingTechnique> toMap(Map<PaintEntity, PaintingTechniqueEntity> entitiesMap);

    Map<PaintEntity, PaintingTechniqueEntity> toEntitiesMap(Map<Paint, PaintingTechnique> map);

}
