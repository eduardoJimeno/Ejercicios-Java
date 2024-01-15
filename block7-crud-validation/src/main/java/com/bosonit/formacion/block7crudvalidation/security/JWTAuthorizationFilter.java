package com.bosonit.formacion.block7crudvalidation.security;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "mySecretKey";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = extractJwtToken(request);
            System.out.println("Received Token: " + jwtToken);
            if (jwtToken != null) {
                Claims claims = validateToken(jwtToken);
                if (claims.get("authorities") != null) {
                    List<String> authorities = (List<String>) claims.get("authorities");
                    logger.debug("Authorities from Token: " + authorities);
                    setUpSpringAuthentication(claims);
                } else {
                    clearSecurityContext();
                }
            } else {
                clearSecurityContext();
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            handleJwtException(response, e);
        }
    }
    private String extractJwtToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return (authenticationHeader != null && authenticationHeader.startsWith(PREFIX))
                ? authenticationHeader.replace(PREFIX, "")
                : null;
    }
    private Claims validateToken(String jwtToken) {
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), null,
                //authorities.stream().map(SimpleGrantedAuthority::new).toList()
                authorities.stream().map(authority -> new SimpleGrantedAuthority("ROLE_" + authority)).toList()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    private void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
    private void handleJwtException(HttpServletResponse response, JwtException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }

}

