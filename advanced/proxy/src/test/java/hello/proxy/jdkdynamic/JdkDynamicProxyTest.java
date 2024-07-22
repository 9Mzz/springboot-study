package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.instrument.classloading.ShadowingClassLoader;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface            proxy   = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();
        log.info("target.getClass = {}", target.getClass());
        log.info("proxy.getClass = {}", proxy.getClass());
    }


    @Test
    void dynamicB() {
        BInterface            targetB = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(targetB);
        BInterface            proxyB  = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        proxyB.call();
        log.info("targetB.getClass = {}", targetB.getClass());
        log.info("proxyB.getClass = {}", proxyB.getClass());
    }

}
