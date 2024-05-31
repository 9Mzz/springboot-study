package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testMember() {
        Member memberA    = new Member("memberA");
        Member saveMember = memberRepository.save(memberA);
        Member findMember = memberRepository.findById(saveMember.getId())
                .get();
        Assertions.assertThat(saveMember)
                .isEqualTo(findMember);
        Assertions.assertThat(saveMember.getId())
                .isEqualTo(findMember.getId());
        Assertions.assertThat(saveMember.getUserName())
                .isEqualTo(findMember.getUserName());

        log.info("memberA.getCreateBy() = {}", memberA.getCreateBy());
        log.info(" memberA.getModifyBy() = {}", memberA.getModifyBy());
    }

}