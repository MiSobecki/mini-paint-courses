package courses.paint.mini.usecase.producer;

import courses.paint.mini.model.Producer;
import courses.paint.mini.port.RequestProducerPort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllProducersUseCase {

    private final RequestProducerPort requestProducerPort;

    public Set<Producer> execute() {
        return requestProducerPort.getAll();
    }

}
