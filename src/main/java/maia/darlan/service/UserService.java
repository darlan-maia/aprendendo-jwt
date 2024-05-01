package maia.darlan.service;

import lombok.RequiredArgsConstructor;
import maia.darlan.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UsernameNotFoundException exception = new UsernameNotFoundException("Usuário %s não encontrado".formatted(username));
        return repository.findByUsername(username).orElseThrow(() -> exception);
    }
}
