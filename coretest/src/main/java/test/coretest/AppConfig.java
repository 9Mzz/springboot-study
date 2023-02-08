package test.coretest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.coretest.discount.DiscountPolicy;
import test.coretest.discount.RateDiscountPolicy;
import test.coretest.member.MemberRepository;
import test.coretest.member.MemberRepositoryImpl;
import test.coretest.member.MemberService;
import test.coretest.member.MemberServiceImpl;
import test.coretest.order.OrderService;
import test.coretest.order.OrderServiceImpl;

@Configuration public class AppConfig {

    /**
     * MemberService
     *
     * @return
     */
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    /**
     * DiscountPolicy
     *
     * @return
     */
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    /**
     * MemberRepository
     *
     * @return
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl();
    }

    /**
     * OrderService
     *
     * @return
     */
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(discountPolicy(), memberService());
    }


}
