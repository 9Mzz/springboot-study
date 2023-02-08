package test.coretest.discount;

import test.coretest.member.MemberVo;

public interface DiscountPolicy {

    int discount(MemberVo memberVo, int price);

}
