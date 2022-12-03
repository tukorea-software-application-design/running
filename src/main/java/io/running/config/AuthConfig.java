package io.running.config;


import io.running.filter.MockJwtFilter;
import io.running.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AuthConfig {

    private final MemberService memberService;

    @Bean
    @Profile("local")
    public AuthFilterContainer mockAuthFilter() {
        log.info("Initializing local AuthFilter");
        AuthFilterContainer authFilterContainer = new AuthFilterContainer();
        authFilterContainer.setAuthFilter(new MockJwtFilter(memberService));
        return authFilterContainer;
    }
}
