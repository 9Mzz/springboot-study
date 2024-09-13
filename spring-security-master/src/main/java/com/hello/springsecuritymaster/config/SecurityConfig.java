package com.hello.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logoutSuccess")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/logoutSuccess")
                .logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("/logoutSuccess"))
                .deleteCookies("JSESSIONID", "CUSTOM_COOKIE")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .addLogoutHandler((request, response, authentication) -> {
                    request.getSession()
                            .invalidate();
                    SecurityContextHolder.getContextHolderStrategy()
                            .getContext()
                            .setAuthentication(null);
                    SecurityContextHolder.getContextHolderStrategy()
                            .clearContext();
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
