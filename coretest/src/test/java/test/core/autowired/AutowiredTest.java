package test.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import test.core.member.MemberVo;

import java.lang.reflect.Member;
import java.util.Optional;

public class AutowiredTest {


    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        //호출 안됨
        @Autowired(required = false)
        public void setNoBean1(MemberVo member) {
            System.out.println("setNoBean1 = " + member);
        }

        //null 호출
        @Autowired
        public void setNoBean2(@Nullable MemberVo member) {
            System.out.println("setNoBean2 = " + member);
        }

        //Optional.empty 호출
        @Autowired(required = false)
        public void setNoBean3(Optional<MemberVo> noBean3) {
            System.out.println("setNoBean3 = " + noBean3);
        }


    }

}
