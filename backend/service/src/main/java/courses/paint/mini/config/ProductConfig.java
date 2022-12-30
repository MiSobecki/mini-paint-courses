package courses.paint.mini.config;

import courses.paint.mini.port.RequestModelingProductPort;
import courses.paint.mini.port.RequestPaintPort;
import courses.paint.mini.usecase.product.GetAllModelingProductsUseCase;
import courses.paint.mini.usecase.product.GetAllPaintsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductConfig {

    private final RequestModelingProductPort requestModelingProductPort;
    private final RequestPaintPort requestPaintPort;

    @Bean
    public GetAllModelingProductsUseCase getAllModelingProductsUseCase() {
        return new GetAllModelingProductsUseCase(requestModelingProductPort);
    }
    @Bean
    public GetAllPaintsUseCase getAllPaintsUseCase() {
        return new GetAllPaintsUseCase(requestPaintPort);
    }

}
