package com.hello.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
                .requestMatchers("/admin")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated());

        http.formLogin(Customizer.withDefaults());

        http.exceptionHandling(ex -> ex
/*                .authenticationEntryPoint((request, response, authException) -> {
                    System.out.println("authException = " + authException.getMessage());
                    response.sendRedirect("/login");
                })*/
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    System.out.println("accessDeniedException = " + accessDeniedException.getMessage());
                    response.sendRedirect("/login");
                }));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
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
