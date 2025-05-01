package com.projeyonetim.gateway.security;

import io.jsonwebtoken.JwtException;
import com.projeyonetim.gateway.security.JwtProps;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final JwtProps jwtProps;

    public boolean isValid(String token) {
        try {
            Jwts.parser()                       //  <-- aynısı
                    .setSigningKey(jwtProps.getSecret())
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {              // any signature/exp error
            return false;
        }
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProps.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}