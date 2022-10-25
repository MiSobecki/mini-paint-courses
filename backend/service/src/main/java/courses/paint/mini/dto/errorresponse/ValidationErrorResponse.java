package courses.paint.mini.dto.errorresponse;

import courses.paint.mini.enums.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {

    private Map<String, String> errors;
    private ExceptionType exceptionType;
    private LocalDateTime localDateTime;

}
