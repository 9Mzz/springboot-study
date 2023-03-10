package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService  orderService;

    @BeforeEach
    public void beforeAct() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService  = appConfig.orderService();

    }

    @Test
    void createOrder() {
        Long   memberId = 1L;
        Member memberA  = new Member(memberId, "nameA", Grade.VIP);

        memberService.join(memberA);
        Order order = orderService.createOrder(memberA.getId(), "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}
