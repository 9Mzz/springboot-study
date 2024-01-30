package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //psvm
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService      memberservice      = applicationContext.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberservice.join(memberA);

        Member findMemberA = memberservice.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find member = " + findMemberA.getName());
    }
}
