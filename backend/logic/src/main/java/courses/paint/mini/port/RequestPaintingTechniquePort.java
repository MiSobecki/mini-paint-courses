package courses.paint.mini.port;

import courses.paint.mini.model.course.PaintingTechnique;

import java.util.Set;

public interface RequestPaintingTechniquePort {

    Set<PaintingTechnique> getAll();

}
