package com.api.hackweek.configuration.security;

import com.api.hackweek.exceptions.JwtSecurityException;
import com.api.hackweek.services.UserService;
import com.api.hackweek.utils.JwtUtils;
import com.api.hackweek.utils.constants.ErrorMessages;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.io.IOException;

@Component
@NonNullApi
@RequiredArgsConstructor
public class JwtSecurityFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final HandlerMappingIntrospector introspection;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = jwtUtils.getTokenFromHeader(request).orElseThrow(() -> new JwtSecurityException(ErrorMessages.TOKEN_NOT_FOUND));
            String login = jwtUtils.validateToken(token);

            UserDetails user = userService.loadUserByUsername(login);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

            filterChain.doFilter(request, response);
        } catch (JwtSecurityException | UsernameNotFoundException e) {
            resolver.resolveException(request, response, null, e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspection);
        HttpMethod requestMethod = HttpMethod.valueOf(request.getMethod());

        return SecurityFilter.WHITELIST.entrySet().stream()
                .filter(entry -> entry.getKey().equals(requestMethod))
                .flatMap(entry -> entry.getValue().stream().map(uri -> mvc.pattern(entry.getKey(), uri)))
                .anyMatch(matcher -> matcher.matches(request));
    }
}
