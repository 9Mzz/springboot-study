package hello.datajpa.repository.member;

import hello.datajpa.domain.Address;
import hello.datajpa.domain.Member;
import hello.datajpa.domain.MemberDto;
import hello.datajpa.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager    em;


    @Test
    void testMember() {
        // class jdk.proxy2.$Proxy116 (스프링 데이터 JPA가 MemberRepository 인터페이스 보고 프록시 객체(구현체)를 만들어서 주입)
        log.info("memberRepository = " + memberRepository.getClass());


        Member memberA    = new Member("memberA");
        Member saveMember = memberRepository.save(memberA);
        Member findMember = memberRepository.findById(saveMember.getId())
                .get();

        // Result
        log.info("memberA : {}", memberA);
        log.info("saveMember : {}", saveMember);
        log.info("findMember : {}", findMember);

        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getUserName()).isEqualTo(memberA.getUserName());
        assertThat(findMember).isEqualTo(memberA);   // JPA 동일성 Check
    }


    @Test
    void testQuery() {
        Member memberA = new Member("memberA", 20);
        Member memberB = new Member("memberB", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> memberA1 = memberRepository.findUser("memberA", 20);
        log.info("memberA = {}", memberA1);
    }


    @Test
    void findMemberDto() {
        Team   teamA   = new Team("teamA");
        Member memberA = new Member("memberA", 20, new Address("Seoul"), teamA);
        memberRepository.save(memberA);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            log.info("dto = {}", dto);
        }
    }

    @Test
    void findByNames() {
        Team teamA = new Team("teamA");

        Member memberA = new Member("memberA", 20, new Address("Seoul"), teamA);
        memberRepository.save(memberA);
        Member memberB = new Member("memberB", 20, new Address("Seoul"), teamA);
        memberRepository.save(memberB);

        List<Member> names = memberRepository.findByNames(Arrays.asList("memberA", "memberB"));
        for (Member name : names) {
            log.info("name = {}", name);
        }


    }

    /**
     * Page 사용 예제 실행 코드
     */
    @Test
    void pagingTest() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberB", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberC", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberD", 20, new Address("Seoul"), teamA));

        int age    = 20;
        int offset = 0;
        int limit  = 3;

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "userName"));

        // Page<Member> memberPage = memberRepository.findMemberByAge(age, pageRequest);
        Slice<Member> memberPage        = memberRepository.findMemberByAge(age, pageRequest);
        List<Member>  memberPageContent = memberPage.getContent();

        for (Member member : memberPageContent) {
            log.info("member = {}", member);
        }
        int size = memberPageContent.size();
        log.info("size = {}", size);
    }

    // DTO로 쉽게 변환하는 방법
    @Test
    void dtoPaging() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberB", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberC", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberD", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberE", 20, new Address("Seoul"), teamA));

        int age    = 20;
        int offset = 0;
        int limit  = 3;

        PageRequest  pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "userName"));
        Page<Member> memberPage  = memberRepository.findMemberByAge(age, pageRequest);

        List<Member> members = memberPage.getContent();
        Page<MemberDto> mapped = memberPage.map(member -> new MemberDto(member.getId(), member.getUserName(), member.getAge(), member.getAddress()
                .getMemberAddress(), member.getTeam()
                .getName()));

        for (MemberDto memberDto : mapped) {
            log.info("memberDto = {}", memberDto);
        }
    }

    /**
     * 스프링 데이터 JPA를 사용한 벌크성 수정 쿼리
     */
    @Test
    void bulkUpdate() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberB", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberC", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberD", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberE", 20, new Address("Seoul"), teamA));

        int result = memberRepository.bulkAgePlus(20);
        log.info("result = {}", result);
    }

    @Test
    void bulkUpdate2() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 5, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberB", 10, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberC", 15, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberD", 20, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberE", 25, new Address("Seoul"), teamA));

        /**
         * 회원을 findById로 다시 조회하면 영속성 컨텍스트에 과거 값이 남아서 문제가 될 수 있다.
         * 다시 조회해야한다면, 영속성 컨텍스트를 초기화해야 한다
         */
        int    resultCount = memberRepository.bulkAgePlus(25);
        Member memberE     = memberRepository.findMemberByUserName("memberE");
        log.info("memberE = {}", memberE);
    }

    /**
     * JPQL 페치 조인
     */
    @Test
    void findMemberLazy() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        memberRepository.save(new Member("memberA", 5, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberB", 10, new Address("Seoul"), teamB));
        em.flush();
        em.clear();

        // when
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            log.info("member.getUserName = {}", member.getUserName());
            log.info("member.teamClass = {}", member.getTeam()
                    .getClass());
            log.info("member.teamName = {}", member.getTeam()
                    .getName());
        }
    }

    @Test
    void findMemberFetchJoin() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        memberRepository.save(new Member("memberA", 5, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberB", 10, new Address("Seoul"), teamB));
        em.flush();
        em.clear();

        // when
        List<Member> members = memberRepository.findMemberFetchJoin();
        for (Member member : members) {
            log.info("member.getUserName 2 = {}", member.getUserName());
            log.info("member.teamClass 2 = {}", member.getTeam()
                    .getClass());
            log.info("member.teamName 2 = {}", member.getTeam()
                    .getName());
        }
    }

    /**
     * EntityGraph
     */
    @Test
    void findMemberEntityGraph() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        memberRepository.save(new Member("memberA", 5, new Address("Seoul"), teamA));
        memberRepository.save(new Member("memberB", 10, new Address("Seoul"), teamB));
        em.flush();
        em.clear();

        // List<Member> members = memberRepository.findAll();
        // List<Member> members = memberRepository.findMemberEntityGraph();
        // List<Member> members = memberRepository.findMemberEntityGraphs();
        List<Member> members = memberRepository.findEntityGraphByUserName("memberA");

        for (Member member : members) {
            log.info("members = {}", member);
        }
    }

    @Test
    void queryHintBefore() {
        Team   teamA   = new Team("teamA");
        Member memberA = new Member("memberA", 5, new Address("Seoul"), teamA);
        Member memberB = new Member("memberB", 5, new Address("Seoul"), teamA);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        em.flush();
        em.clear();

        Member findMemberA = memberRepository.findById(memberA.getId())
                .get();
        findMemberA.setUserName("memberABC");
        em.flush();
    }

    @Test
    void queryHintAfter() {
        Team   teamA   = new Team("teamA");
        Member memberA = new Member("memberC", 5, new Address("Seoul"), teamA);
        Member memberB = new Member("memberD", 5, new Address("Seoul"), teamA);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findReadOnlyByUserName("memberC");
        findMember.setUserName("member2");
        em.flush();
    }

    /**
     * Lock
     */
    @Test
    void lock() {
        Team   teamA   = new Team("teamA");
        Member memberA = new Member("member1", 5, new Address("Seoul"), teamA);
        Member memberB = new Member("member2", 5, new Address("Seoul"), teamA);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        em.flush();
        em.clear();

        Member findMmeber1 = memberRepository.findLockByUserName("member1");
    }

    /**
     * 확장 기능
     */

    /**
     * 사용자 정의 메서드 호출 코드
     */
    @Test
    void callCustom() throws InterruptedException {

        Team   teamA   = new Team("teamA");
        Member memberA = new Member("memberA", 20, new Address("seoul"), teamA);
        memberRepository.save(memberA);

        Thread.sleep(1000);
        memberA.setUserName("memberA-test");

        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(memberA.getId())
                .get();
        log.info("findMember = {}", findMember);
        log.info("findMember getCreateBy = {}", findMember.getCreateBy());
        log.info("findMember getCreatedDate = {}", findMember.getCreatedDate());
        log.info("findMember getLastModifiedBy = {}", findMember.getLastModifiedBy());
        log.info("findMember getLastModifiedDate = {}", findMember.getLastModifiedDate());

    }


}