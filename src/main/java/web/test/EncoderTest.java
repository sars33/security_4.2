package web.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderTest {

    @Test
    public void encryptPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
     String encodedPassword = passwordEncoder.encode("circle");

     System.out.println(encodedPassword);

    }
}
