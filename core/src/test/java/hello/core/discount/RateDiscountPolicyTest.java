package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void discount() {

        //given
        Member testA = new Member(1L, "testA", Grade.VIP);

        //when
        int result = discountPolicy.discount(testA, 13000);

        System.out.println("discount = " + result + "원 할인됨.");
        //then
        assertThat(result).isEqualTo(1300);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void discountFail() {

        //given
        Member testA = new Member(2L, "testA", Grade.GRADE);

        //when
        int result = discountPolicy.discount(testA, 10000);

        System.out.println("discountFail = " + result + "원 할인됨.");
        //then
        assertThat(result).isEqualTo(0);

    }
}