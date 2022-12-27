package courses.paint.mini.usecase.course;

import courses.paint.mini.model.course.PaintingTechnique;
import courses.paint.mini.port.RequestPaintingTechniquePort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllPaintingTechniquesUseCase {

    private final RequestPaintingTechniquePort requestPaintingTechniquePort;

    public Set<PaintingTechnique> execute() {
        return requestPaintingTechniquePort.getAll();
    }

}
