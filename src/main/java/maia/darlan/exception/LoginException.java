package maia.darlan.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginException extends RuntimeException {

    @Builder
    public LoginException(Throwable cause) {
        super("Falha ao fazer login", cause);
    }
}
