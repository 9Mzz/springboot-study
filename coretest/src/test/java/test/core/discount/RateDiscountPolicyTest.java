package test.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.core.member.Grade;
import test.core.member.MemberVo;

// 클래스 수준에서 테스트코드를 돌리면 작성해놓은 모든 테스트케이스가 다 돌아간다.
class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {

        //when
        MemberVo memberVo = new MemberVo(1L, "testVIP", Grade.VIP);
        //then
        int discount = discountPolicy.discount(memberVo, 10000);
        //given
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    //실패 테스트도 만들어 봐야 함.
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given
        MemberVo member = new MemberVo(2L, "testBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}