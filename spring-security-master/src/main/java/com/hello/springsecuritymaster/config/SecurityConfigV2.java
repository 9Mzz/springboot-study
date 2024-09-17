package com.hello.springsecuritymaster.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@EnableWebSecurity
@Configuration
public class SecurityConfigV2 {


    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.formLogin(form -> form.loginPage("/loginPage")
                .loginProcessingUrl("/login`")
                .defaultSuccessUrl("/home")
                .successHandler((request, response, authentication) -> {
                    System.out.println("authentication = " + authentication);
                    response.sendRedirect("/home");
                })
                .usernameParameter("userName")
                .passwordParameter("passWD")
                .failureHandler((request, response, exception) -> {
                    System.out.println(exception.getMessage());
                    response.sendRedirect("/loginPage");
                })
                .permitAll());

        http.logout(logout -> logout.logoutUrl("/logout`")
                .logoutSuccessUrl("/")
                .logoutSuccessHandler((request, response, authentication) -> {
                    System.out.println("authentication = " + authentication);
                    response.sendRedirect("/home");
                })
                .deleteCookies("JSESSIONID")
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

        http.rememberMe(remember -> remember.alwaysRemember(true)
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService())
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("rememberME")
                .key("security")
        );

        http.anonymous(anon -> anon.principal("GUEST")
                .authorities("ROLE_GUEST"));

        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("result=y");
        http.requestCache(cache -> cache.requestCache(requestCache));


        return http.build();
    }


    public UserDetailsService userDetailsService() {
        UserDetails userA = User.withUsername("userA")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails userB = User.withUsername("userB")
                .password("{noop}1234")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userA, userB);
    }

}
