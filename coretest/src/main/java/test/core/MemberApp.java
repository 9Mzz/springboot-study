package test.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.core.member.Grade;
import test.core.member.MemberService;
import test.core.member.MemberVo;

public class MemberApp {

    public static void main(String[] args) {

        //Spring 전환
        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);

        //기존
        //        Appconfig     appconfig     = new Appconfig();
        //        MemberService memberService = appconfig.memberService();

        // ctrl + alt + v 하면 리턴값 생성
        // Id가 1L, 이름이 testA, 등급이 VIP인 testA 생성
        MemberVo testA = new MemberVo(1L, "testA", Grade.VIP);
        memberService.join(testA);

        MemberVo findMember = memberService.findMember(1L);
        //soutv
        System.out.println("new MemberVo = " + testA);
        System.out.println("find MemberVo = " + findMember);


    }


}
