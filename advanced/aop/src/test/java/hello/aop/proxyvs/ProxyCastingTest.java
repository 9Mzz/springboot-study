package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target  = new MemberServiceImpl();
        ProxyFactory      factory = new ProxyFactory(target);
        // JDK 동적 프록시로 변경
        factory.setProxyTargetClass(false);

        // 프록시를 인터페이스로 캐스팅 -> 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 예외 발생
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target  = new MemberServiceImpl();
        ProxyFactory      factory = new ProxyFactory(target);
        // JDK 동적 프록시로 변경
        factory.setProxyTargetClass(true);

        // 프록시를 인터페이스로 캐스팅 -> 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        // CGLIB 동적 프록시를 구현 클래스로 캐스팅 시도 성공,
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }

}
