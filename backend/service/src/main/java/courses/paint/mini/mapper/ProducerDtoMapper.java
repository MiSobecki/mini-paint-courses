package courses.paint.mini.mapper;

import courses.paint.mini.dto.ProducerDto;
import courses.paint.mini.model.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerDtoMapper {

    ProducerDto fromProducer(Producer producer);

}
