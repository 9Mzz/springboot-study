package com.hello.springsecuritymaster.config;

import jakarta.security.auth.message.config.AuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.formLogin(login -> login.loginPage("/loginPage")
                .loginProcessingUrl("/loginProce")
                .defaultSuccessUrl("/home")
                .usernameParameter("userName")
                .passwordParameter("/passWd")
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/home");
                })
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/loginPage");
                })
                .permitAll());

        http.rememberMe(remember -> remember.alwaysRemember(true)
                .tokenValiditySeconds(3600)
                .rememberMeCookieName("REMEMBER")
                .rememberMeParameter("remember-me")
                .key("security")
                .userDetailsService(userDetailsService()));

        http.anonymous(anon -> anon.principal("GUEST")
                .authorities("ROLE_GUEST"));

        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("result=true");
        http.requestCache(cache -> cache.requestCache(requestCache));

        http.sessionManagement(session -> session.maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/loginPage"));


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
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
