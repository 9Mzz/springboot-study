package test.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import test.core.discount.FixDiscountPolicy;
import test.core.member.Grade;
import test.core.member.MemberRepositoryImpl;
import test.core.member.MemberVo;

class OrderServiceImplTest {


    @Test
    void createOrder() {
        MemberRepositoryImpl memberRepository = new MemberRepositoryImpl();
        memberRepository.save(new MemberVo(1L, "testName", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl( memberRepository, new FixDiscountPolicy());

        OrderVo order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}