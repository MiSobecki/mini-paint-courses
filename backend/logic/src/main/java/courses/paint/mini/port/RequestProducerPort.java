package courses.paint.mini.port;

import courses.paint.mini.model.Producer;

import java.util.Set;

public interface RequestProducerPort {

    Set<Producer> getAll();

}
