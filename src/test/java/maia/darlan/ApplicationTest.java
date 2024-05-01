package maia.darlan;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ApplicationTest {

    @Test
    void test() {
        String joao = new BCryptPasswordEncoder().encode("joao");
        String maria = new BCryptPasswordEncoder().encode("maria");

        System.out.println(joao);
        System.out.println(maria);
    }
}