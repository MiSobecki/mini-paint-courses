package courses.paint.mini.config;

import courses.paint.mini.port.RequestProducerPort;
import courses.paint.mini.usecase.producer.GetAllProducersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProducerConfig {

    private final RequestProducerPort requestProducerPort;

    @Bean
    public GetAllProducersUseCase getAllProducersUseCase() {
        return new GetAllProducersUseCase(requestProducerPort);
    }

}
