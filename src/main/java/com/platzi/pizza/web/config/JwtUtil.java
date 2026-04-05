package com.platzi.pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Duration;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static String SECRET_KEY = "pl4tz1_p1zz4";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    public String create(String username) {
        return JWT.create()
            .withSubject(username)
            .withIssuer("platzi-pizza")
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plus(Duration.ofDays(15)))
            .sign(ALGORITHM);
    }
}
