package courses.paint.mini.adapter;

import courses.paint.mini.mapper.course.PaintingTechniqueMapper;
import courses.paint.mini.model.course.PaintingTechnique;
import courses.paint.mini.port.RequestPaintingTechniquePort;
import courses.paint.mini.repository.course.PaintingTechniqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestPaintingTechniqueAdapter implements RequestPaintingTechniquePort {

    private final PaintingTechniqueRepository paintingTechniqueRepository;
    private final PaintingTechniqueMapper paintingTechniqueMapper;

    @Override
    public Set<PaintingTechnique> getAll() {
        return paintingTechniqueRepository.findAll().stream()
                .map(paintingTechniqueMapper::toPaintingTechnique)
                .collect(Collectors.toSet());
    }

}
