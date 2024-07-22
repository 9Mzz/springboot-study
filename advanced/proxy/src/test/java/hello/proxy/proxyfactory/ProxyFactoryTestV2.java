package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.common.service.ServiceInterfaceImpl;
import hello.proxy.commonv2.advice.TimeAdviceV2;
import hello.proxy.commonv2.service.ServiceInterfaceV2;
import hello.proxy.commonv2.service.ServiceInterfaceV2Impl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;


@Slf4j
public class ProxyFactoryTestV2 {
    @Test
    @DisplayName("인터페이스가 있으면 JDK 프록시를 사용합니다.")
    void interfaceProxy() {
        ServiceInterfaceV2 target       = new ServiceInterfaceV2Impl();
        ProxyFactory       proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdviceV2());
        ServiceInterfaceV2 proxy = (ServiceInterfaceV2) proxyFactory.getProxy();

        log.info("target class = {}", proxy.getClass());
        log.info("proxy = {}", proxy.getClass());

        proxy.save();


    }
}
