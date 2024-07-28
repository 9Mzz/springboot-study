package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderSerivce;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext ac            = new AnnotationConfigApplicationContext();
        MemberService      memberService = ac.getBean("memberService", MemberService.class);
        OrderSerivce       orderService  = ac.getBean("orderService", OrderSerivce.class);

        long   memberId = 1L;
        Member member   = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 15000);
        System.out.println("order = " + order);
    }
}
