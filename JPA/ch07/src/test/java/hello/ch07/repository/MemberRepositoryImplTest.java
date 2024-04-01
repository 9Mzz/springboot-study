package hello.ch07.repository;

import hello.ch07.mapped.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member memberA = new Member();
        memberA.setName("name");
        memberA.setEmail("abab@gmail.com");
        memberRepository.save(memberA);
    }
}