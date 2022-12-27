package courses.paint.mini.usecase.product;

import courses.paint.mini.model.product.ModelingProduct;
import courses.paint.mini.port.RequestModelingProductPort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllModelingProductsUseCase {

    private final RequestModelingProductPort requestModelingProductPort;

    public Set<ModelingProduct> execute() {
        return requestModelingProductPort.getAll();
    }

}
