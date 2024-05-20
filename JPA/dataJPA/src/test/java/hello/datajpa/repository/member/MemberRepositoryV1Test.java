package hello.datajpa.repository.member;

import hello.datajpa.domain.Address;
import hello.datajpa.domain.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryV1Test {

    @Autowired
    MemberRepositoryV1 memberRepositoryV1;


    @Test
    void testMember() {
        Member memberA    = new Member("memberA");
        Member saveMember = memberRepositoryV1.save(memberA);
        Member findMember = memberRepositoryV1.findById(saveMember.getId())
                .get();

        log.info("memberA = {}", memberA);
        log.info("saveMember = {}", saveMember);
        log.info("findMember = {}", findMember);

        log.info("memberA.getClass = {}", memberA.getClass());
        log.info("findMember.getClass = {}", findMember.getClass());

        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getUserName()).isEqualTo(memberA.getUserName());
        assertThat(findMember).isEqualTo(memberA);
    }

    @Test
    void findByUsernameAndAgeGreaterThen() {
        Member memberA = new Member("memberA", 20, new Address("서울"));
        Member memberB = new Member("memberB", 20);
        memberRepositoryV1.save(memberA);
        memberRepositoryV1.save(memberB);


        List<Member> findMemberA = memberRepositoryV1.findByUsernameAndAgeGreaterThen("memberA", 20);
        for (Member member : findMemberA) {
            log.info("findByUsernameAndAgeGreaterThen = {}", member);
        }

    }

}