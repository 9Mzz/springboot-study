package hello.ch09.repository;

import hello.ch09.Ch09Application;
import hello.ch09.domain.Address;
import hello.ch09.domain.Member;
import hello.ch09.domain.PhoneNumber;
import hello.ch09.domain.ZipCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = Ch09Application.class)
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void Test() {
        Member memberA = new Member();
        memberA.setName("memberA");
        memberA.setAddress(new Address("Korea", "Seoul", "myHome", new ZipCode("15020", "203400")));
        memberA.setPhoneNumber(new PhoneNumber("SEOUL", "155-203"));
        memberRepository.save(memberA);
    }


}