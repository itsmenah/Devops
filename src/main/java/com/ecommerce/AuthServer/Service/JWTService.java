package com.ecommerce.AuthServer.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private final String secret = "3a729a18653535a1d549058825ea601e4b7dcd6e6cda17c1f4468b94fff7f21d";
    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


    public String getToken(String userName) {

        Map<String,Object> claims= new HashMap<>();


        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .and()
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }


    public String extractUserName(String token)
    {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails)
    {
        return (extractUserName(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public boolean isTokenExpired(String token)
    {
        return getClaims(token).getExpiration().before(new Date());
    }
}
