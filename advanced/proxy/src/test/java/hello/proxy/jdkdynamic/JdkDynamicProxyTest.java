package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {

        AInterface            targetA  = new AImpl();
        TimeInvocationHandler handlerA = new TimeInvocationHandler(targetA);
        AInterface            proxy    = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handlerA);
        proxy.call();
        log.info("targetClass = {}", targetA.getClass());
        log.info("proxyClass = {}", proxy.getClass());

    }

    @Test
    void dynamicB() {
        BInterface            targetB  = new BImpl();
        TimeInvocationHandler handlerB = new TimeInvocationHandler(targetB);
        BInterface            proxyB   = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handlerB);

        proxyB.call();

        log.info("targetClass = {}", targetB.getClass());
        log.info("proxyClass = {}", proxyB.getClass());

    }

}
