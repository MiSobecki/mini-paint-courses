package courses.paint.mini.config;

import courses.paint.mini.port.RequestModelingProductPort;
import courses.paint.mini.usecase.product.GetAllModelingProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelingProductConfig {

    private final RequestModelingProductPort requestModelingProductPort;

    @Bean
    public GetAllModelingProductsUseCase getAllModelingProductsUseCase() {
        return new GetAllModelingProductsUseCase(requestModelingProductPort);
    }

}
