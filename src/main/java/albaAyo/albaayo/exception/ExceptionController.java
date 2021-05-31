package albaAyo.albaayo.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public ExceptionMessage runtimeException(Exception e) {

        return new ExceptionMessage(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionMessage methodArgumentNotValidException(Exception e) {

        return new ExceptionMessage(e.getMessage());
    }
}
