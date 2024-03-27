package hello.ch01.repsitory;

import hello.ch01.domain.Member;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    void save() {
        Member member = new Member();
        member.setUsername("지한");
        member.setAge(2);

        // 저장
        Member saveResult = memberRepository.save(member);
        // 검색
        Member findMember = memberRepository.findById(saveResult.getId()).get();
        // 수정
        Member newMember = new Member();
        newMember.setAge(30);
        newMember.setUsername("9M");
        memberRepository.updateMember(findMember.getId(), newMember);
        Member updatedMember = memberRepository.findById(findMember.getId()).get();


        log.info("saveResult = {}", saveResult);
        log.info("findMember = {}", findMember);
        log.info("updateMember = {}", updatedMember);
    }

    @Test
    void flushTest() {
        Member memberA = new Member();
        memberA.setUsername("memberA");
        memberA.setAge(20);


        Member memberB = new Member();
        memberB.setUsername("memberB");
        memberB.setAge(25);

        Member memberC = new Member();
        memberC.setUsername("memberC");
        memberC.setAge(27);

        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberRepository.save(memberC);


    }
}