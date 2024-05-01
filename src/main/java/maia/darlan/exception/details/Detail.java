package maia.darlan.exception.details;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Detail {

    private String message;

    @Builder.Default
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String path;

    private String rootCause;
}
