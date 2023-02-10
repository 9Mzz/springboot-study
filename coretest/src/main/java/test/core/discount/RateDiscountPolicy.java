package test.core.discount;

import test.core.member.Grade;
import test.core.member.MemberVo;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10; //10% 할인

    /**
     * @param memberVo
     * @param price
     * @return 할인 대상 금액
     */
    @Override
    public int discount(MemberVo memberVo, int price) {

        if (memberVo.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }


}
