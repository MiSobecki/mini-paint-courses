package courses.paint.mini.adapter;

import courses.paint.mini.mapper.product.ModelingProductMapper;
import courses.paint.mini.model.product.ModelingProduct;
import courses.paint.mini.port.RequestModelingProductPort;
import courses.paint.mini.repository.product.ModelingProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestModelingProductAdapter implements RequestModelingProductPort {

    private final ModelingProductRepository modelingProductRepository;
    private final ModelingProductMapper modelingProductMapper;

    @Override
    public Set<ModelingProduct> getAll() {
        return modelingProductRepository.findAll().stream()
                .map(modelingProductMapper::toModelingProduct)
                .collect(Collectors.toSet());
    }

}
