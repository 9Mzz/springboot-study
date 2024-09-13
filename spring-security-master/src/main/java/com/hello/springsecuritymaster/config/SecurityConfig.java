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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.logout(logout -> logout
                //// 로그아웃이 발생하는 URL 을 지정합니다 (기본값은 /logout 입니다)
                .logoutUrl("/logoutProc")
                // 로그아웃이 발생하는 RequestMatcher 을 지정한다. logoutUrl 보다 우선적입니다
                // method를 지정하지 않으면 logout URL이 어떤 HTTP 메서드로든 요청될 때 로그아웃 할 수 있습니다
                .logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc", "POST"))
                // 로그아웃이 발생한 후 리다이렉션 될 URL입니다. 기본값은 /login?logout 입니다
                .logoutSuccessUrl("/logoutSuccess")
                // 사용할 LogoutSuccessHandler 를 설정합니다, 이것이 지정되면 logoutSuccessUrl(String)은 무시됩니다
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.sendRedirect("/logoutSuccess");
                })
                // 로그아웃 성공 시 제거될 쿠키의 이름을 지정할 수 있습니다
                .deleteCookies("JSESSIONID", "CUSTOM_COOKIE")
                // HttpSession 을 무효화해야 하는 경우 true (기본값), 그렇지 않으면 false
                .invalidateHttpSession(true)
                // 로그아웃 시 SecurityContextLogoutHandler 가 인증(Authentication)을 삭제 해야 하는지 여부를 명시한다
                .clearAuthentication(true)
                // 기존의 로그아웃 핸들러 뒤에 새로운 LogoutHandler를 추가 합니다
                .addLogoutHandler((request, response, authentication) -> {
                })
                // logoutUrl(), RequestMatcher() 의 URL 에 대한 모든 사용자의 접근을 허용합니다
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
