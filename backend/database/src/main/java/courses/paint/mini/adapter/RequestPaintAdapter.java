package courses.paint.mini.adapter;

import courses.paint.mini.mapper.product.PaintMapper;
import courses.paint.mini.model.product.Paint;
import courses.paint.mini.port.RequestPaintPort;
import courses.paint.mini.repository.product.PaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RequestPaintAdapter implements RequestPaintPort {

    private final PaintRepository paintRepository;
    private final PaintMapper paintMapper;

    @Override
    public Set<Paint> getAll() {
        return paintRepository.findAll().stream()
                .map(paintMapper::toPaint)
                .collect(Collectors.toSet());
    }

}
