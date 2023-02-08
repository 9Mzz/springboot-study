package test.coretest.discount;

import test.coretest.member.Grade;
import test.coretest.member.MemberVo;

public class DiscountPolicyA implements DiscountPolicy {

    int discountFigure = 1000;


    /**
     * memberVo를 받아서 할인정책 시행
     *
     * @param memberVo
     * @param price
     * @return
     */
    @Override
    public int discount(MemberVo memberVo, int price) {
        if (memberVo.getGrade() == Grade.VIP) {
            return discountFigure;
        } else {
            return 0;
        }
    }


}
