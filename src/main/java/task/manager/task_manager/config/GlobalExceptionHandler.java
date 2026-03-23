package task.manager.task_manager.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomApiResponse<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex,
                                                                   HttpServletRequest request) {
        String details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toError)
                .collect(Collectors.joining(" | "));

        return new CustomApiResponse<>(
                Map.of(
                        "path", request.getRequestURI(),
                        "details", details
                ),
                true,
                HttpStatus.BAD_REQUEST,
                "Datos invalidos. Revisa los campos enviados."
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CustomApiResponse<Map<String, Object>> handleUnreadable(HttpMessageNotReadableException ex,
                                                                   HttpServletRequest request) {
        return new CustomApiResponse<>(
                Map.of(
                        "path", request.getRequestURI(),
                        "details", "JSON mal formado o tipos de datos incorrectos"
                ),
                true,
                HttpStatus.BAD_REQUEST,
                "No se pudo procesar la solicitud."
        );
    }

    private String toError(FieldError error) {
        return error.getField() + ": " + error.getDefaultMessage();
    }
}

