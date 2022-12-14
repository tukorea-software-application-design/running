package io.running.config;


import io.running.filter.MockJwtFilter;
import io.running.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AuthConfig {

    private final MemberService memberService;

    @Bean
    public AuthFilterContainer mockAuthFilter() {
        log.info("Initializing local AuthFilter");
        AuthFilterContainer authFilterContainer = new AuthFilterContainer();
        authFilterContainer.setAuthFilter(new MockJwtFilter(memberService));
        return authFilterContainer;
    }
}
