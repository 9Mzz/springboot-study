package hello.core;


import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig     appConfig     = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "MemberA", Grade.VIP);

        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);

        System.out.println("new memberA = " + memberA);
        System.out.println("findMember = " + findMember);


    }

}
