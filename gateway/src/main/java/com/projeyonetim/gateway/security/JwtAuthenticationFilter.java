package com.projeyonetim.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtTokenUtil tokenUtil;
    private static final List<String> WHITELIST = List.of(
            "/api/users/login", "/api/users/register");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String path = exchange.getRequest().getPath().value();

        // beyaz liste - doğrulama yok
        if (WHITELIST.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        if (!tokenUtil.isValid(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // ► TOKEN GEÇERLİ  →  Authentication objesini bağlama
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        tokenUtil.getEmail(token),
                        null,
                        List.of()      // Rol/authority gerekiyorsa buraya ekle
                );

        return chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder
                        .withAuthentication(authentication));
    }
}
