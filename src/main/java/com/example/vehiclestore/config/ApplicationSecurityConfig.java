/* package com.example.vehiclestore.config;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import com.example.vehiclestore.DAO.entities.User;
import com.example.vehiclestore.DAO.repository.UserRepository;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class ApplicationSecurityConfig {

private final RSAPublicKey key;
private final RSAPrivateKey priv;
private final UserRepository userRepository;
public ApplicationSecurityConfig(@Value("${jwt.public-key}") RSAPublicKey key, @Value("${jwt.private-key}") RSAPrivateKey priv, UserRepository userRepository) {
this.key = key;
this.priv = priv;
this.userRepository = userRepository;
}

@Bean
public UserDetailsService userDetailsService() {
return username -> userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found"));
}

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationProvider authenticationProvider() {
DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
provider.setPasswordEncoder(passwordEncoder());
provider.setUserDetailsService(userDetailsService());
return provider;
}

@Bean
public JwtDecoder jwtDecoder() {
return NimbusJwtDecoder.withPublicKey(key).build();
}

@Bean
public JwtEncoder jwtEncoder() {
JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
return new NimbusJwtEncoder(jwks);
}


@Bean
public JwtAuthenticationConverter jwtAuthenticationConverter() {
JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
grantedAuthoritiesConverter.setAuthorityPrefix("");
JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
return jwtAuthenticationConverter;
}
}




 */