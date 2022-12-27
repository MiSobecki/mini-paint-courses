package courses.paint.mini.config;

import courses.paint.mini.port.RequestPaintingTechniquePort;
import courses.paint.mini.usecase.course.GetAllPaintingTechniquesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaintingTechniqueConfig {

    private final RequestPaintingTechniquePort requestPaintingTechniquePort;

    @Bean
    public GetAllPaintingTechniquesUseCase getAllPaintingTechniquesUseCase() {
        return new GetAllPaintingTechniquesUseCase(requestPaintingTechniquePort);
    }

}
