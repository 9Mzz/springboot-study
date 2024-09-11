package com.hello.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

        http.authorizeHttpRequests(auth -> auth
                // "GUEST 역할을 가진 사용자만 /anonymous URL 에 접근할 수 있음
                .requestMatchers("/anonymous")
                .hasRole("GUEST")
                // /authentication 와 /anonymousContext URL 은 인증이 없어도 접근 가능
                .requestMatchers("/authentication", "anonymousContext")
                .permitAll()
                .anyRequest()
                .authenticated());

        http.formLogin(Customizer.withDefaults());

        http.anonymous(anon -> anon
                // 익명 사용자의 이름을 guest 로 설정
                .principal("guest")
                // 익명 사용자에게 ROLE_GUEST 권한을 부여함
                .authorities("ROLE_GUEST"));

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userA = User.withUsername("userA")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails userB = User.withUsername("userB")
                .password("{noop}1234")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userA, userB);
    }


}
