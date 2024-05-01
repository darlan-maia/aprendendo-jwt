package maia.darlan.controller;

import lombok.RequiredArgsConstructor;
import maia.darlan.exception.LoginException;
import maia.darlan.model.dto.LoginDTO;
import maia.darlan.model.dto.TokenDTO;
import maia.darlan.model.entity.Role;
import maia.darlan.model.entity.User;
import maia.darlan.security.JWTUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserDetailsService service;

    private final JWTUtils jwtUtils;

    private final PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(final @RequestBody LoginDTO login) {

        UserDetails userDetails = service.loadUserByUsername(login.getUsername());

        if (!encoder.matches(login.getPassword(), userDetails.getPassword())) throw LoginException.builder().build();

        String token = jwtUtils.generateToken(userDetails.getUsername(), userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

        TokenDTO dto = new TokenDTO(token);

        return ResponseEntity.ok().body(dto);
    }
}
