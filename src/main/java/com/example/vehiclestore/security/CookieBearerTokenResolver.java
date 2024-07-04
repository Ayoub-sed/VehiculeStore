package com.example.vehiclestore.security;

import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

// Custom implementation of BearerTokenResolver that retrieves the token from a cookie
public class CookieBearerTokenResolver implements BearerTokenResolver {

    private final String cookieName; // Name of the cookie containing the token

    public CookieBearerTokenResolver(String cookieName) {
        this.cookieName = cookieName;
    }
  @Override
    public String resolve(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        if (cookie != null) {
        String token = cookie.getValue();
        if (StringUtils.hasText(token)) {
            return token;
        }
        }
        return null;
    }

   
}
