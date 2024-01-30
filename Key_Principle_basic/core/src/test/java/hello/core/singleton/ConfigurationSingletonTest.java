package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //Original
        MemberRepository  memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberServiceImpl memberService    = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl  orderService     = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //        System.out.println("Original -> memberRepository = " + memberRepository);
        //        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        //        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        Assertions.assertThat(memberService.getMemberRepository())
                .isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository())
                .isSameAs(memberRepository);

    }
}
