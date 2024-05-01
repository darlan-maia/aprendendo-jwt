package maia.darlan.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SecurityException extends RuntimeException {

    @Builder.Default
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    @Builder
    public SecurityException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
}
