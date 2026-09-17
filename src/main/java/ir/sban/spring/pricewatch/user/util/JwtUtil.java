package ir.sban.spring.pricewatch.user.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration-ms:86400000}")
    private long expirationMs;
    private SecretKey signingKey;
    @PostConstruct // or constructor after @Value injection
    void init() {
        signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String email, long id) {
        Date issued = new Date(System.currentTimeMillis());
        Date expiration = new Date(issued.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("email", email)
                .setIssuedAt(issued)
                .setExpiration(expiration)
                .signWith(signingKey)
                .compact();
    }
}
