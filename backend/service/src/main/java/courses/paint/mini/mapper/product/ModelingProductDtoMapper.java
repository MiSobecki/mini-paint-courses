package courses.paint.mini.mapper.product;

import courses.paint.mini.dto.product.ModelingProductDto;
import courses.paint.mini.model.product.ModelingProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelingProductDtoMapper {

    ModelingProductDto fromModelingProduct(ModelingProduct modelingProduct);

}
