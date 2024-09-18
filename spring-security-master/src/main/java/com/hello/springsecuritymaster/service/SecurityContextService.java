package com.hello.springsecuritymaster.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    public void securityContext() {
        System.out.println("SecurityContextService.securityContext");
        SecurityContextHolderStrategy holderStrategy  = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext               securityContext = holderStrategy.getContext();


        System.out.println("holderStrategy = " + holderStrategy);
        System.out.println("securityContext = " + securityContext);

    }

}
