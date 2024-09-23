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

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated());

        http.formLogin(Customizer.withDefaults());
        // false 인 경우
        //invalidSessionUrl X expiredUrl X -> This session has been expired
        //invalidSessionUrl O expiredUrl X -> This session has been expired
        //invalidSessionUrl X expiredUrl O -> expiredUrl()에 설정된 URL로 리다이렉션
        //invalidSessionUrl O expiredUrl O -> invalidSessionUrl()에 설정된 URL로 리다이렉션
        // true 인 경우 -> 인증 차단

        //추천 -> maxSessionsPreventsLogin(false), expiredUrl 만 사용
        http.sessionManagement(session -> session
                // 이미 만료된 세션으로 요청을 하는 사용자를 특정 엔드포인트로 리다이렉션 할 Url 을 지정한다
                // .invalidSessionUrl("/invalidSession")
                // 사용자당 최대 세션 수를 제어한다. 기본값은 무제한 세션을 허용한다
                .maximumSessions(1)
                // true 이면 최대 세션 수(maximumSessions(int))에 도달했을 때 사용자의 인증을 방지한다
                // false(기본 설정)이면 인증하는 사용자에게 접근을 허용하고 기존 사용자의 세션은 만료된다
                .maxSessionsPreventsLogin(false)
                // 세션을 만료하고 나서 리다이렉션 할 URL 을 지정한다
                .expiredUrl("/expired"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }


}
