package com.example.vehiclestore.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import                                                                                                                                                       
org.springframework.security.oauth2.server.resource.web. BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

// Custom implementation of BearerTokenResolver that retrieves the token from a cookie
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{

    @Value("${jwt.cookie-name}")
    private final String jwtCookieName; // Name of the cookie containing the token

    private final AuthenticationProvider authenticationProvider ;// Name of the cookie containing the token
   
    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests((request) -> request

                .requestMatchers("/authentication/**","/webjars/**").permitAll()
                
                .anyRequest().authenticated()
            )

            .csrf(csrf -> csrf.disable())

            /* .httpBasic(Customizer.withDefaults()) */
            .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(customAuthenticationEntryPoint))

            .oauth2ResourceServer(oauth2 -> oauth2

                .bearerTokenResolver(new CookieBearerTokenResolver(jwtCookieName))

                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .exceptionHandling(exeption -> exeption.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())

                .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                )
                .authenticationProvider(authenticationProvider);
        return http.build();
    }

}

