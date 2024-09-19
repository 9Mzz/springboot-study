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
        http.authorizeHttpRequests(auth -> auth.anyRequest()
                        .authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("loginProc")
                        .defaultSuccessUrl("/", false)

                        .failureUrl("/failed")
                        .usernameParameter("userName")
                        .passwordParameter("passWd")
                        .successHandler((request, response, authentication) -> {
                            System.out.println("authentication = " + authentication);
                            response.sendRedirect("/");
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("exception = " + exception.getMessage());
                            response.sendRedirect("/loginPage");
                        })
                        .permitAll());
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
