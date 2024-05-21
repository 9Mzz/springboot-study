package hello.datajpa.repository.member;

import hello.datajpa.domain.Address;
import hello.datajpa.domain.Member;
import hello.datajpa.domain.Team;
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

    /**
     * JPA 페이징 테스트 코드
     */

    @Test
    public void paging() {
        Team teamA = new Team("teamA");
        memberRepositoryV1.save(new Member("memberA", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberB", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberC", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberD", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberE", 20, new Address("Seoul"), teamA));

        int age    = 20;
        int offset = 0;
        int limit  = 3;

        List<Member> pageResult = memberRepositoryV1.findByPage(age, offset, limit);
        for (Member pageMember : pageResult) {
            log.info("pageMember = {}", pageMember);
        }
        Long totalCount = memberRepositoryV1.totalCount(age);
        log.info("totalCount = {}", totalCount);
    }

    @Test
    void bulkUpdate() {
        Team teamA = new Team("teamA");
        memberRepositoryV1.save(new Member("memberA", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberB", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberC", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberD", 20, new Address("Seoul"), teamA));
        memberRepositoryV1.save(new Member("memberE", 20, new Address("Seoul"), teamA));

        int resultCount = memberRepositoryV1.bulkAgePlus(20);
        log.info("resultCount = {}", resultCount);

    }

}