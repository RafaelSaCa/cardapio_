package com.rfsaca.cardapio.cardapio.config;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rfsaca.cardapio.cardapio.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer")) {
            try {

                String token = authorizationHeader.substring("Bearer ".length());
                Optional<JWTUserData> optUser = tokenService.validaToken(token);

                if (optUser.isPresent()) {
                    JWTUserData userData = optUser.get();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData,null,
                                    userData.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.name()))).toList());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

                filterChain.doFilter(request, response);

            } catch (Exception e) {
                response.setHeader("error", e.getLocalizedMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }

}