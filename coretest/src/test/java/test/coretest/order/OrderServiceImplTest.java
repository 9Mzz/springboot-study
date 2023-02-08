package test.coretest.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.coretest.AppConfig;
import test.coretest.member.Grade;
import test.coretest.member.MemberService;
import test.coretest.member.MemberVo;

class OrderServiceImplTest {


    OrderService  orderService;
    MemberService memberService;

    @BeforeEach
    public void beforeAct() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService  = appConfig.orderService();
    }

    @Test
    public void createOrderTest() {
        Long   tId    = 1L;
        String tName  = "testName";
        Grade  tGrade = Grade.VIP;

        MemberVo testMember = new MemberVo(tId, tName, tGrade);

        memberService.save(testMember);

        String  titemName = "testItem";
        int     tPrice    = 150000;
        OrderVo order     = orderService.createOrder(testMember.getMemberId(), titemName, tPrice);

        System.out.println("order = " + order);
        System.out.println("order.totalPrice() = " + order.totalPrice());

    }

}