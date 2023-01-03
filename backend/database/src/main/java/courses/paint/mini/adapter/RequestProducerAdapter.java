package courses.paint.mini.adapter;

import courses.paint.mini.mapper.ProducerMapper;
import courses.paint.mini.model.Producer;
import courses.paint.mini.port.RequestProducerPort;
import courses.paint.mini.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestProducerAdapter implements RequestProducerPort {

    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    @Override
    public Set<Producer> getAll() {
        return producerRepository.findAll().stream()
                .map(producerMapper::toProducer)
                .collect(Collectors.toSet());
    }

}
