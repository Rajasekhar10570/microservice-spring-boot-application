package com.raja.orderservice.external.intercept;

import com.raja.orderservice.service.TokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class OAuthRequestInterceptor implements RequestInterceptor {

    private final TokenService tokenService;

    public OAuthRequestInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void apply(RequestTemplate template) {
        String token = tokenService.extractToken();  // Get token from TokenService
        log.info(token);
        if (token != null) {
            template.header("Authorization", "Bearer " + token);
        }
    }
}
