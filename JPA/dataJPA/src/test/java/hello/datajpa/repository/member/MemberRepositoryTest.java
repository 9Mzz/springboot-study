package hello.datajpa.repository.member;

import hello.datajpa.domain.Address;
import hello.datajpa.domain.Member;
import hello.datajpa.domain.MemberDto;
import hello.datajpa.domain.Team;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


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
        Team teamA = new Team("teamA");

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
        List<Member> members     = memberPage.getContent();

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

        int resultCount = memberRepository.bulkAgePlus(20);
        log.info("resultCount = {}", resultCount);
    }


}