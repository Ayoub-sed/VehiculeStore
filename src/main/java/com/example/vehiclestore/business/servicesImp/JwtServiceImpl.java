package com.example.vehiclestore.business.servicesImp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import com.example.vehiclestore.business.services.JwtService;
import java.util.stream.Collectors;
import java.time.Instant;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtEncoder encoder;

    @Value("${jwt.public.key}")
    private String publicKey;

    @Value("${jwt.private.key}")
    private String privateKey;

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.cookie-name}")
    private String cookieName;
 
    public JwtServiceImpl(JwtEncoder encoder) {
        this.encoder = encoder;
    }


    @Override
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = expiration;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public ResponseCookie generateJwtCookie(String jwt) {
        return ResponseCookie.from(cookieName, jwt)
                .path("/")
                .maxAge(24 * 60 * 60) // 24 hours
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .build();
    }
    @Override
    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(cookieName, "")
                .path("/")
                .build();
    }
}