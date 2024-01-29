package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {
    //psvm
    public static void main(String[] args) {
        AppConfig     appConfig = new AppConfig();
        MemberService service   = appConfig.memberService();


        //        MemberService service = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        service.join(memberA);

        Member findMemberA = service.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find member = " + findMemberA.getName());
    }
}
