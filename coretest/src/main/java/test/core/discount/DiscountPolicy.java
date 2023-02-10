package test.core.discount;

import test.core.member.MemberVo;

public interface DiscountPolicy {

    /**
     * @param memberVo
     * @param price
     * @return 할인 대상 금액
     */
    int discount(MemberVo memberVo, int price);

}
