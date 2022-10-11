package courses.paint.mini.mapper.product;

import courses.paint.mini.entity.product.PaintEntity;
import courses.paint.mini.mapper.ProducerMapper;
import courses.paint.mini.model.product.Paint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProducerMapper.class)
public interface PaintMapper {

    Paint toPaint(PaintEntity paintEntity);

    PaintEntity fromPaint(Paint paint);

}
