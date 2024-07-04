package com.example.vehiclestore.security;

import java.io.IOException;
import java.time.Instant;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.vehiclestore.exeptions.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {

    ErrorResponse errorResponse = ErrorResponse.builder()
.timestamp(Instant.now())
.error("Unauthorized")
.status(HttpServletResponse.SC_UNAUTHORIZED)
.message(authException.getMessage())
.path(request.getRequestURI())
.build();

response.setContentType("application/json");
response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
final ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule());
mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
mapper.writeValue(response.getOutputStream(),errorResponse);
}

}
