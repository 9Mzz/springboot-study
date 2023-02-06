package test.testcore.order;

import org.junit.jupiter.api.Test;
import test.testcore.member.Grade;
import test.testcore.member.MemberService;
import test.testcore.member.MemberServiceImpl;
import test.testcore.member.MemberVo;

class OrderServiceImplTest {

    OrderService  orderService  = new OrderServiceImpl();
    MemberService memberService = new MemberServiceImpl();

    @Test
    void createOrder() {

        Long   tId    = 1L;
        String tName  = "testA";
        Grade  tGrade = Grade.VIP;

        MemberVo testMember = new MemberVo(tId, tName, tGrade);
        memberService.join(testMember);

        OrderVo testOrder = orderService.createOrder(testMember.getId(), "testItem", 1000000);

        System.out.println("testOrder = " + testOrder);

    }
}