package test.coretest.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.coretest.member.MemberRepository;
import test.coretest.member.MemberRepositoryImpl;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBean.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면,중복 오류가 발생한다")
    void findBeanByTypeFail() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepositoryImpl.class));
    }

    @Test
    public void findBeanByType() {
        MemberRepositoryImpl memberRepository1 = ac.getBean("memberRepository1", MemberRepositoryImpl.class);

        System.out.println("memberRepository1 = " + memberRepository1);

    }

    @Test
    public void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key);
            System.out.println("value =  " + beansOfType.get(key));
        }


    }

    @Configuration static class sameBean {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemberRepositoryImpl();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemberRepositoryImpl();
        }

    }

}
