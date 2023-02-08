package test.coretest.discount;

import test.coretest.member.Grade;
import test.coretest.member.MemberVo;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountRate = 10;

    @Override
    public int discount(MemberVo memberVo, int price) {

        if (memberVo.getGrade() == Grade.VIP) {

            return price * discountRate / 100;
        } else {

            return 0;
        }

    }


}
