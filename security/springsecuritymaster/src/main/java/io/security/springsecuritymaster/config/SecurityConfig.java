package io.security.springsecuritymaster.config;


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
        http.authorizeHttpRequests(auth -> auth.anyRequest()
                                               .authenticated())
            .formLogin(Customizer.withDefaults());
        return http.build();
    }


    //    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails buildA = User.withUsername("userA")
                                 .password("{noop}1234")
                                 .roles("USER")
                                 .build();
        UserDetails buildB = User.withUsername("userB")
                                 .password("{noop}1234")
                                 .roles("USER")
                                 .build();
        UserDetails buildC = User.withUsername("userC")
                                 .password("{noop}1234")
                                 .roles("USER")
                                 .build();
        return new InMemoryUserDetailsManager(buildA, buildB, buildC);


    }

}
