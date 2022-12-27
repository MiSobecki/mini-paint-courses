package courses.paint.mini.port;

import courses.paint.mini.model.product.ModelingProduct;

import java.util.Set;

public interface RequestModelingProductPort {

    Set<ModelingProduct> getAll();

}
