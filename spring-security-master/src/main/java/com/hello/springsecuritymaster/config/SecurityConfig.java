package com.hello.springsecuritymaster.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
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
        http.csrf(AbstractHttpConfigurer::disable);

        http.formLogin(form -> form.loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/home");
                })
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/login");
                })
                .permitAll());
        http.rememberMe(remember -> remember.alwaysRemember(true)
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService())
                .rememberMeCookieName("remember-me")
                .rememberMeParameter("REMEMBER")
                .key("security"));

        http.anonymous(anon -> anon.principal("GUEST")
                .authorities("ROLE_GUEST"));
        http.logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.sendRedirect("/home");
                })
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "CUSTOM_COOKIE")
                .addLogoutHandler((request, response, authentication) -> {
                    request.getSession()
                            .invalidate();
                    SecurityContextHolder.getContextHolderStrategy()
                            .setContext(null);
                    SecurityContextHolder.getContextHolderStrategy()
                            .getContext()
                            .setAuthentication(null);
                })
                .permitAll());
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("result=true");

        http.requestCache(request -> request.requestCache(requestCache));


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
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
