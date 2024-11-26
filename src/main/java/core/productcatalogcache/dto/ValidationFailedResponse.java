package core.productcatalogcache.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ValidationFailedResponse {
    protected HttpStatus status;
    protected String message;
    private final List<String> errorMessages;
}
