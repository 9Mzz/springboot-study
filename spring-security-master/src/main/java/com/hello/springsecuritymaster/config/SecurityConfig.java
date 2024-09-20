package com.hello.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // SecurityContext 를 명시적으로 저장할 것이지 아닌지의 여부 설정, 기본값은 true 입니다
        // true 이면 SecurityContextHolderFilter, false 이면 SecurityContextPersistanceFilter 가 실행됩니다
        http.securityContext(context -> context.requireExplicitSave(true));


        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userA = User.withUsername("userA").password("{noop}1234").roles("USER").build();
        return new InMemoryUserDetailsManager(userA);
    }

}
