package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderSerivceTest {
    MemberService memberService;
    OrderSerivce  orderSerivce;

    @BeforeEach
    void before() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderSerivce = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long   memberId = 1L;
        Member member   = new Member(memberId, "itemA", Grade.VIP);
        memberService.join(member);
        Order order = orderSerivce.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice())
                .isEqualTo(1000);
        System.out.println("order = " + order);
    }
}