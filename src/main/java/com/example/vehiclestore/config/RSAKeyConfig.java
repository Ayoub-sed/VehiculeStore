/* package com.example.vehiclestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class RSAKeyConfig {

    @Value("${jwt.public-key}")
    private String publicKeyString;

    @Value("${jwt.private-key}")
    private String privateKeyString;

    @Bean
    public RSAPublicKey rsaPublicKey() throws Exception {
        String publicKeyPEM = publicKeyString
              .replaceAll("-----BEGIN (.*)-----", "")
              .replaceAll("-----END (.*)----", "")
              .replaceAll("\\s", ""); // Remove newlines and spaces
        
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws Exception {
        String privateKeyPEM = privateKeyString 
              .replaceAll("-----BEGIN (.*)-----", "")
              .replaceAll("-----END (.*)----", "")
              .replaceAll("\\s", ""); // Remove newlines and spaces

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }
} */