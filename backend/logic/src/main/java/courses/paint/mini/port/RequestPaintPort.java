package courses.paint.mini.port;

import courses.paint.mini.model.product.Paint;

import java.util.Set;

public interface RequestPaintPort {

    Set<Paint> getAll();

}
