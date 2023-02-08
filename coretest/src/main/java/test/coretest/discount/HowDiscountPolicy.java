package test.coretest.discount;

import test.coretest.member.Grade;
import test.coretest.member.MemberVo;

public class HowDiscountPolicy implements DiscountPolicy {

    private int discontAmount = 1000;

    @Override
    public int discount(MemberVo memberVo, int price) {

        if (memberVo.getGrade() == Grade.VIP) {
            return discontAmount;
        } else {
            return 0;
        }

    }


}
