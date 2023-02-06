package test.testcore.discount;

import test.testcore.member.Grade;
import test.testcore.member.MemberVo;

public class DiscountPolicyImpl implements DiscountPolicy {

    int discountRate = 2000;

    /**
     * @param memberVo
     * @param price
     * @return 할인 대상 금액(1000원, 2000원 등)
     */
    @Override
    public int discountInt(MemberVo memberVo, int price) {

        if (memberVo.getGrade() == Grade.VIP) {
            return discountRate;
        } else {
            return 0;
        }
    }


}
