package courses.paint.mini.mapper.product;

import courses.paint.mini.dto.product.PaintDto;
import courses.paint.mini.model.product.Paint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaintDtoMapper {

    PaintDto fromPaint(Paint paint);

}
