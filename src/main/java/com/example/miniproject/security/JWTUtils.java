package com.example.miniproject.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class JWTUtils {
    // This is used to log messages for a specific system or application component
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    // This sets the default expiration for the JSON Web Token to 24 hours
    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;


    public String generateJwtToken(MyUserDetails myUserDetails) {
        // myUserDetails.getUsername() is the user's email address
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpMS))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build().parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {

        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch(SecurityException e) {
            logger.log(Level.SEVERE, "Invalid JWT signature {}" + e.getMessage());
        } catch(MalformedJwtException e) {
            logger.log(Level.SEVERE, "Invalid JWT Malformed JWT Exception {}" + e.getMessage());
        } catch(ExpiredJwtException e) {
            logger.log(Level.SEVERE, "JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
