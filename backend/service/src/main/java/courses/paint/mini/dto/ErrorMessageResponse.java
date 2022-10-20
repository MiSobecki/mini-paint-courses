package courses.paint.mini.dto;

import courses.paint.mini.enums.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorMessageResponse {

    private String errorMessage;
    private ExceptionType exceptionType;
    private LocalDateTime localDateTime;

}
