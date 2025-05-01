package com.projeyonetim.gateway.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProps {
    private String secret;
    private long expiration;
}
