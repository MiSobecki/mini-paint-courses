package courses.paint.mini.mapper;

import courses.paint.mini.entity.ProducerEntity;
import courses.paint.mini.model.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    Producer toProducer(ProducerEntity producerEntity);

    ProducerEntity fromProducer(Producer producer);

}
