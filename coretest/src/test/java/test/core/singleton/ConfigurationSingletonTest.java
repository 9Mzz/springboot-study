package test.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.core.Appconfig;
import test.core.member.MemberRepository;
import test.core.member.MemberServiceImpl;
import test.core.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);

        MemberServiceImpl memberService    = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl  orderService     = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository  memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //        System.out.println("memberService = " + memberService);
        //        System.out.println("orderService = " + orderService);
        //        System.out.println("memberRepository = " + memberRepository);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository -> memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac   = new AnnotationConfigApplicationContext(Appconfig.class);
        Appconfig                          bean = ac.getBean(Appconfig.class);
        System.out.println("bean = " + bean);
        System.out.println("bean.getClass() = " + bean.getClass());

    }

}

