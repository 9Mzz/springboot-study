package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import hello.proxy.pureproxy.proxy.code.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        Subject            realSubject = new RealSubject();
        ProxyPatternClient client      = new ProxyPatternClient(realSubject);
        client.excute();
        client.excute();
        client.excute();
    }

    @Test
    void cacheProxyTest() {
        Subject    realSubject = new RealSubject();
        CacheProxy cacheProxy  = new CacheProxy(realSubject);

        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
        client.excute();
        client.excute();
        client.excute();
    }


}
