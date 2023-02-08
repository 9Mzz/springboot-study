package test.coretest.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.coretest.AppConfig;
import test.coretest.member.Grade;
import test.coretest.member.MemberService;
import test.coretest.member.MemberVo;

class OrderServiceImplTest {

    MemberService memberService;
    OrderService  orderService;

    @BeforeEach
    public void beforeAct() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService  = appConfig.orderService();
    }


    @Test
    void createOrder() {
        MemberVo testUser = new MemberVo(1L, "testName", Grade.VIP);
        memberService.save(testUser);

        OrderVo testItem = orderService.createOrder(testUser.getId(), "testItem", 13000);

        System.out.println("testItem = " + testItem);
        System.out.println("testItem.totalPrice() = " + testItem.totalPrice());

    }
}