package com.example.vehiclestore.security;

public class CookieBearerTokenResolver {

    public static String resolveToken(String cookie) {
        if (cookie == null) {
            return null;
        }
        String[] parts = cookie.split(";");
        for (String part : parts) {
            if (part.trim().startsWith("Bearer")) {
                return part.substring(7);
            }
        }
        return null;
    }

    
}
