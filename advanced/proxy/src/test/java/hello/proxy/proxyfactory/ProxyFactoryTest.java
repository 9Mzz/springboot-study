package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.common.service.ServiceInterfaceImpl;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;


@Slf4j
public class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 JDK 프록시를 사용합니다.")
    void interfaceProxy() {
        ServiceInterface target       = new ServiceInterfaceImpl();
        ProxyFactory     proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("interfaceProxy target.getClass = {}", target.getClass());
        log.info("interfaceProxy proxy.getClass = {}", proxy.getClass());

        proxy.save();

        // proxyFactory 를 통해 만들었을 때 사용 가능
        Assertions.assertThat(AopUtils.isAopProxy(proxy))
                .isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy))
                .isTrue();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy))
                .isFalse();

    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용.")
    void concreteProxy() {
        ConcreteService target       = new ConcreteService();
        ProxyFactory    proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("concreteProxy target.getClass = {}", target.getClass());
        log.info("concreteProxy proxy.getClass = {}", proxy.getClass());

        proxy.call();

        // proxyFactory 를 통해 만들었을 때 사용 가능
        Assertions.assertThat(AopUtils.isAopProxy(proxy))
                .isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy))
                .isFalse();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy))
                .isTrue();
    }

    @Test
    @DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용.")
    void proxyTargetClass() {
        ServiceInterface target       = new ServiceInterfaceImpl();
        ProxyFactory     proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("interfaceProxy target.getClass = {}", target.getClass());
        log.info("interfaceProxy proxy.getClass = {}", proxy.getClass());

        proxy.save();

        // proxyFactory 를 통해 만들었을 때 사용 가능
        Assertions.assertThat(AopUtils.isAopProxy(proxy))
                .isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy))
                .isFalse();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy))
                .isTrue();
    }
}
