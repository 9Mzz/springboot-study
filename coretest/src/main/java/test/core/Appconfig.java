package test.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.core.discount.DiscountPolicy;
import test.core.discount.RateDiscountPolicy;
import test.core.member.MemberRepository;
import test.core.member.MemberRepositoryImpl;
import test.core.member.MemberService;
import test.core.member.MemberServiceImpl;
import test.core.order.OrderService;
import test.core.order.OrderServiceImpl;

@Configuration public class Appconfig {

    // Key : memberService , Value : new MemberServiceImpl(memberRepository())
    // 생성자 주입(DI)
    // @Bean 을 붙여주면 스프링 컨테이너에 등록된다.

    // @Bean memberService -> new MemberRepositoryImpl()
    // @bean orderService -> new MemberRepositoryImpl()

    @Bean
    public MemberService memberService() {
        System.out.println("call -> Appconfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call -> Appconfig.orderService");

        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //        return null;
    }


    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call -> Appconfig.memberRepository");

        return new MemberRepositoryImpl();
    }


    public DiscountPolicy discountPolicy() {
        System.out.println("call -> Appconfig.discountPolicy");

        //        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
