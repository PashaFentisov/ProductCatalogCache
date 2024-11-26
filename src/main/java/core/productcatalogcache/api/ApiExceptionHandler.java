package core.productcatalogcache.api;

import core.productcatalogcache.dto.FailureResponse;
import core.productcatalogcache.dto.ValidationFailedResponse;
import core.productcatalogcache.exception.EntityValidationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(@NotNull RuntimeException e) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new FailureResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(EntityValidationException.class)
    protected ResponseEntity<Object> handleEntityValidationException(@NotNull EntityValidationException e) {
        ArrayList<String> errorMessages = new ArrayList<>();
        e.getErrors().getFieldErrors()
                .forEach(error -> errorMessages.add(error.getDefaultMessage()));
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ValidationFailedResponse(HttpStatus.BAD_REQUEST, e.getMessage(), errorMessages));
    }
}
