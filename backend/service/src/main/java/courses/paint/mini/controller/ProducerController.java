package courses.paint.mini.controller;

import courses.paint.mini.dto.ProducerDto;
import courses.paint.mini.mapper.ProducerDtoMapper;
import courses.paint.mini.usecase.producer.GetAllProducersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final GetAllProducersUseCase getAllProducersUseCase;
    private final ProducerDtoMapper producerDtoMapper;

    @GetMapping
    public List<ProducerDto> getAll() {
        return getAllProducersUseCase.execute().stream()
                .map(producerDtoMapper::fromProducer)
                .toList();
    }

}
