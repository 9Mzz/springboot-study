package test.testcore.discount;

import test.testcore.member.MemberVo;

public interface DiscountPolicy {

    /**
     *
     * @param memberVo
     * @param price
     * @return 할인 대상 금액(1000원, 2000원 등)
     */
    int discountInt(MemberVo memberVo, int price);

}
