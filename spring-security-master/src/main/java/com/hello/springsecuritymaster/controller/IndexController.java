package com.hello.springsecuritymaster.controller;

import com.hello.springsecuritymaster.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    SecurityContextService contextService;

    @GetMapping("/")
    public Authentication index(Authentication authentication) {
        return authentication;
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/anonymous")
    public String annonymous() {
        return "anonymous";
    }

    // Security 가 현재 사용자 정보를 Authentication 로 주입해주고 받는다.
    @GetMapping("/authentication")
    public String authentication(Authentication authentication) {
        // 사용자가 익명인지 검사
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "anonymous";
        } else {
            return "not anonymous";
        }
    }


    //@CurrentSecurityContext -> 현재 SecurityContext 를 매개변수로 주입, 현재 요청의 보안 정보를 가지고 있음
    @GetMapping("/anonymousContext")
    public String anonymousContext(@CurrentSecurityContext SecurityContext context) {
        // 사용자 아이디 또는 principal 를 반환, "guest"를 반환
        return context.getAuthentication()
                .getName();
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }

}
