package com.example.miniproject.security;

@Service
public class JWTUtils {
    // This is used to log messages for a specific system or application component
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    // This sets the default expiration for the JSON Web Token to 24 hours
    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;






}
