package com.prashantjain.yummyrest.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Use the generated Base64 key here
    private final String SECRET_KEY = "replace_with_your_generated_base64_key";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // Token valid for 1 hour

    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Subject is typically the user's email or username
                .setIssuedAt(new Date()) // Token issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiry time
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Use HS256 for signing
                .compact();
    }
}
