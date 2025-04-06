package task.manager.task_manager.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.manager.task_manager.model.user.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(User user){
        return generateToken(user, expiration);
    }

    public String generateToken(User user, long expiration){
        return Jwts.builder()
                .id(user.getId())
                .claims(Map.of("name", user.getFullName(), "email", user.getEmail()))
                .subject(user.getEmail())
                .issuedAt(new java.util.Date())
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * expiration)))
                .signWith(getSingKey())
                .compact();
    }

    private SecretKey getSingKey() {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

}
