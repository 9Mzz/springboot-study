package test.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import test.core.Appconfig;
import test.core.member.Grade;
import test.core.member.MemberService;
import test.core.member.MemberVo;

class OrderServiceTest {

    Appconfig appconfig = new Appconfig();

    MemberService memberService = appconfig.memberService();
    OrderService  orderService  = appconfig.orderService();

    @Test
    void createOrder() {
        long     memberId = 1L;
        MemberVo member   = new MemberVo(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        OrderVo order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}