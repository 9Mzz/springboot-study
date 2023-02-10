package test.core.discount;

import test.core.member.Grade;
import test.core.member.MemberVo;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;   //1000원 할인

    /**
     * @param memberVo
     * @param price
     * @return 할인 대상 금액
     */
    @Override
    public int discount(MemberVo memberVo, int price) {

        if (memberVo.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {

            return 0;
        }

    }
}
