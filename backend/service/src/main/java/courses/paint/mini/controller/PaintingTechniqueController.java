package courses.paint.mini.controller;

import courses.paint.mini.dto.course.PaintingTechniqueDto;
import courses.paint.mini.mapper.course.PaintingTechniqueDtoMapper;
import courses.paint.mini.usecase.course.GetAllPaintingTechniquesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/painting-techniques")
@RequiredArgsConstructor
public class PaintingTechniqueController {

    private final GetAllPaintingTechniquesUseCase getAllPaintingTechniquesUseCase;
    private final PaintingTechniqueDtoMapper paintingTechniqueMapper;

    @GetMapping
    public List<PaintingTechniqueDto> getAll() {
        return getAllPaintingTechniquesUseCase.execute().stream()
                .map(paintingTechniqueMapper::fromPaintingTechnique)
                .toList();
    }

}
