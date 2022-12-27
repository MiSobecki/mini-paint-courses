package courses.paint.mini.usecase.product;

import courses.paint.mini.model.product.Paint;
import courses.paint.mini.port.RequestPaintPort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllPaintsUseCase {

    private final RequestPaintPort requestPaintPort;

    public Set<Paint> execute() {
        return requestPaintPort.getAll();
    }

}
