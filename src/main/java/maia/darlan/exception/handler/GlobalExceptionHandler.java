package maia.darlan.exception.handler;

import maia.darlan.exception.LoginException;
import maia.darlan.exception.SecurityException;
import maia.darlan.exception.details.Detail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Detail> handleException(HttpServletRequest request, LoginException exception) {

        final Detail detail = Detail.builder()
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .rootCause((exception.getCause() != null) ? exception.getCause().getMessage() : null)
                .build();

        return ResponseEntity.badRequest().body(detail);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Detail> handleException(HttpServletRequest request, SecurityException exception) {

        System.out.println("Entrou aqui");

        final Detail detail = Detail.builder()
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .rootCause(exception.getCause().getMessage())
                .build();

        return ResponseEntity.status(detail.getStatus()).body(detail);
    }
}
