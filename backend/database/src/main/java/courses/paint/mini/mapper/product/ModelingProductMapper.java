package courses.paint.mini.mapper.product;

import courses.paint.mini.entity.product.ModelingProductEntity;
import courses.paint.mini.mapper.ProducerMapper;
import courses.paint.mini.model.product.ModelingProduct;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = ProducerMapper.class)
public interface ModelingProductMapper {

    ModelingProductEntity fromModelingProduct(ModelingProduct modelingProduct);

    ModelingProduct toModelingProduct(ModelingProductEntity modelingProductEntity);

    Set<ModelingProduct> toModelingProducts(Set<ModelingProductEntity> modelingProductEntities);

    Set<ModelingProductEntity> fromModelingProducts(Set<ModelingProduct> modelingProducts);

}
