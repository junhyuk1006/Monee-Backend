package com.example.moneebackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;
    private final long expirationMillis;

    public JwtProvider(@Value("${jwt.secret}") String secret,@Value("${jwt.expiration}") long expirationMillis){
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(String email){
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(expirationMillis);

        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String getSubject(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
