package hello.ch07.repository;

import hello.ch07.mapped.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member memberA = new Member();
        memberA.setName("name");
        memberA.setEmail("abab@gmail.com");
        Member savedMember = memberRepository.save(memberA);
        log.info("savedMember = {}", savedMember);
        Member findMember = memberRepository.findById(savedMember.getId());
        log.info("findMember = {}", findMember);
    }
}