package com.hello.springsecuritymaster.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class CustomAuthenticationProviderV2 implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {

        String loginId  = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 아이디 검증
        // 패스워드 검증

        return new UsernamePasswordAuthenticationToken(loginId, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}