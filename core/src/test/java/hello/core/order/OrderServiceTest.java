package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService  orderService  = new OrderServiceImpl();


    @Test
    void createOrder() {
        Long   memberId = 1L;
        Member memberA  = new Member(memberId, "nameA", Grade.VIP);

        memberService.join(memberA);
        Order order = orderService.createOrder(memberA.getId(), "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}
