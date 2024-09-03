package io.security.springsecuritymaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        // .loginPage("/loginPage")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/", false)
                        .failureUrl("/loginPage?error")
                        .usernameParameter("userId")
                        .passwordParameter("passWd")
                        .successHandler((request, response, authentication) -> {
                            System.out.println("authentication = " + authentication);
                            response.sendRedirect("/home");
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("exception = " + exception.getMessage());
                            response.sendRedirect("/loginPage");
                        }));

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
