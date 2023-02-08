package test.coretest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.coretest.discount.DiscountPolicy;
import test.coretest.discount.DiscountPolicyB;
import test.coretest.member.MemberRepository;
import test.coretest.member.MemberRepositoryImpl;
import test.coretest.member.MemberService;
import test.coretest.member.MemberServiceImpl;
import test.coretest.order.OrderService;
import test.coretest.order.OrderServiceImpl;

@Configuration public class AppConfig {


    @Bean
    public DiscountPolicy discountPolicy() {
        return new DiscountPolicyB();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberService(), discountPolicy());
    }


}
