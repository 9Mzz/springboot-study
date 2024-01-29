package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderSerivceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderSerivce  orderSerivce  = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long   memberId = 1L;
        Member member   = new Member(memberId, "itemA", Grade.VIP);
        memberService.join(member);
        Order order = orderSerivce.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice())
                .isEqualTo(1000);
    }
}