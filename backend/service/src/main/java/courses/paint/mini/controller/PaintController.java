package courses.paint.mini.controller;

import courses.paint.mini.dto.product.PaintDto;
import courses.paint.mini.mapper.product.PaintDtoMapper;
import courses.paint.mini.usecase.product.GetAllPaintsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/paints")
@RequiredArgsConstructor
public class PaintController {

    private final GetAllPaintsUseCase getAllPaintsUseCase;
    private final PaintDtoMapper paintMapper;

    @GetMapping
    public List<PaintDto> getAll() {
        return getAllPaintsUseCase.execute().stream()
                .map(paintMapper::fromPaint)
                .toList();
    }

}
