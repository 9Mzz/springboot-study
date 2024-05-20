package hello.datajpa.repository.member;

import ch.qos.logback.classic.Logger;
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
public class PureJPATest {

    @Autowired
    MemberRepositoryV1 memberRepositoryV1;

    @Autowired
    MemberRepository memberRepository;


    @Test
    void basicCRUDV1() {
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");

        memberRepositoryV1.save(memberA);
        memberRepositoryV1.save(memberB);

        // 단건 조회 검증
        Member findMemberA = memberRepositoryV1.findById(memberA.getId())
                .get();
        Member findMemberB = memberRepositoryV1.findById(memberB.getId())
                .get();

        findMemberA.setUserName("memberTest!!!!!!!!!!!!");

        // List 조회 검증
        List<Member> members = memberRepositoryV1.findAll();
        assertThat(members.size()).isEqualTo(2);

        // Count 검증
        Long count = memberRepositoryV1.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepositoryV1.delete(memberA);
        memberRepositoryV1.delete(memberB);

        Long deleteCount = memberRepositoryV1.count();
        assertThat(deleteCount).isEqualTo(0);
    }

    @Test
    void basicCRUDV2() {
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // 단건 조회 검증
        Member findMemberA = memberRepository.findById(memberA.getId())
                .get();
        Member findMemberB = memberRepository.findById(memberB.getId())
                .get();

        assertThat(findMemberA).isEqualTo(memberA);
        assertThat(findMemberB).isEqualTo(memberB);

        findMemberA.setUserName("memberTest!!!!!!!!!!!!");

        // List 조회 검증
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);

        // 카운트 검증
        Long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(memberA);
        memberRepository.delete(memberB);
        Long deleteCount = memberRepository.count();
    }


}
