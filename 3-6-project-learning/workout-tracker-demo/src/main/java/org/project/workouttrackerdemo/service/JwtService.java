package org.project.workouttrackerdemo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.project.workouttrackerdemo.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JwtService {

    final String secret;

    @Value("${security.jwt.expiration}")
    long expiration;

    @Value("${security.jwt.refresh.expiration}")
    long refreshExpiration;


    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            secret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public SecretKey generateKey() {
        byte[] keyByte = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        try {

            return Jwts.builder()
                    .subject(email)
                    .claims(claims)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(generateKey())
                    .compact();
        } catch (JwtException e) {
            throw new JwtException("Failed to generate access token.");
        }
    }

    public String generateRefreshToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        try {

            return Jwts.builder()
                    .subject(email)
                    .claims(claims)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + refreshExpiration))
                    .signWith(generateKey())
                    .compact();
        } catch(JwtException e) {
            throw new JwtException("Failed to generate refresh token.");
        }
    }

    private Claims extractClaims(String token) {
        try {

            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new JwtException("This is an invalid token.");
        }
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean verifyToken(String token, UserPrincipal userPrincipal) {
        return extractEmail(token).equals(userPrincipal.getEmail()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return new Date(System.currentTimeMillis()).after(extractClaims(token).getExpiration());
    }

}
