package maia.darlan.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import maia.darlan.exception.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationInMillis;

    public String generateToken(String username, List<String> roles) {

        final Instant instant = Instant.now().plus(expirationInMillis, ChronoUnit.MILLIS);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(instant))
                .claim("role", roles)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(key()).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw SecurityException.builder().message("Token expirado").status(HttpStatus.BAD_REQUEST).cause(e).build();
        } catch (UnsupportedJwtException e) {
            throw SecurityException.builder().message("Formato do token não compatível com as configurações da aplicação").status(HttpStatus.BAD_REQUEST).cause(e).build();
        } catch (MalformedJwtException e) {
            throw SecurityException.builder().message("Token mal formado").status(HttpStatus.BAD_REQUEST).cause(e).build();
        } catch (SignatureException e) {
            throw SecurityException.builder().message("Assinatura do token ausente ou errada").status(HttpStatus.BAD_REQUEST).cause(e).build();
        } catch (IllegalArgumentException e) {
            throw SecurityException.builder().message("Argumento ilegal").status(HttpStatus.BAD_REQUEST).cause(e).build();
        }
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
