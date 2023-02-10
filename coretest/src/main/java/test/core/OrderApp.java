package test.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.core.member.Grade;
import test.core.member.MemberService;
import test.core.member.MemberVo;
import test.core.order.OrderService;
import test.core.order.OrderVo;

public class OrderApp {

    public static void main(String[] args) {


        //Spring 전환
        ApplicationContext ac            = new AnnotationConfigApplicationContext(Appconfig.class);
        MemberService      memberService = ac.getBean("memberService", MemberService.class);
        OrderService       orderService  = ac.getBean("orderService", OrderService.class);

        //기존
        //        Appconfig appconfig = new Appconfig();
        //        MemberService memberService = appconfig.memberService();
        //        OrderService  orderService  = appconfig.orderService();

        long     memberId = 1L;
        MemberVo member   = new MemberVo(memberId, "memberA", Grade.VIP);

        memberService.join(member);
        OrderVo order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);

    }
}
