package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.common.service.ServiceInterfaceImpl;
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
        log.info("target.getClass = {}", target.getClass());
        log.info("proxy.getClass = {}", proxy.getClass());

        proxy.save();

        // proxyFactory 를 통해 만들었을 때 사용 가능
        Assertions.assertThat(AopUtils.isAopProxy(proxy))
                .isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy));
        Assertions.assertThat(AopUtils.isCglibProxy(proxy))
                .isFalse();

    }
}
