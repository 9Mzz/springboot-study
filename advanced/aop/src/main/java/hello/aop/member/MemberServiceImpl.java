package hello.aop.member;

import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test Value")
    public String hello(String param) {
        return "ok";
    }

    @Override
    public int helloV2(int param) {
        return param;
    }

    public String internal(String param) {
        return "ok";
    }
}
