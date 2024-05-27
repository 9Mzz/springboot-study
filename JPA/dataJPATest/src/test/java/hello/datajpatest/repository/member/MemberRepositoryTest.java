package hello.datajpatest.repository.member;

import com.sun.source.tree.MemberReferenceTree;
import hello.datajpatest.domain.Address;
import hello.datajpatest.domain.Member;
import hello.datajpatest.domain.Team;
import hello.datajpatest.domain.dto.MemberDto;
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

import java.util.List;

@Slf4j
@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    DataMemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void crudTest() {
        Team   teamA   = new Team("teamA");
        Member memberA = new Member("memberA", 20, new Address("Seoul", "Sadang"), teamA);
        memberRepository.save(memberA);

        em.flush();
        em.clear();

        List<Member> findMember = memberRepository.findMemberByMemberName("memberA");
        for (Member member : findMember) {
            log.info("member : {}", member);
        }
    }

    @Test
    void dtotest() {
        Team   teamA   = new Team("teamA");
        Member memberA = new Member("memberA", 20, new Address("Seoul", "Sadang"), teamA);
        memberRepository.save(memberA);
        em.flush();
        em.clear();

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            log.info("dto: {}", dto);
        }
    }

    @Test
    void pagingTest() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberB", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberC", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberD", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberE", 20, new Address("Seoul", "Sadang"), teamA));

        em.flush();
        em.clear();

        int age    = 20;
        int offset = 0;
        int limit  = 3;

        PageRequest  pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "memberName"));
        Page<Member> memberPage  = memberRepository.findMemberByAge(age, pageRequest);

/*        List<Member> content = memberPage.getContent();
        for (Member member : content) {
            log.info("member = {}", member);
        }*/

        Page<MemberDto> memberDtos = memberPage.map(member -> new MemberDto(member.getId(), member.getMemberName(), member.getAge(), member.getAddress()
                .getStreet(), member.getAddress()
                .getCity(), member.getTeam()
                .getTeamName()));

        for (MemberDto memberDto : memberDtos) {
            log.info("memberDto = {}", memberDto);
        }
    }

    @Test
    void bulkUpdateTest() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberB", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberC", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberD", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberE", 20, new Address("Seoul", "Sadang"), teamA));

        em.flush();
        em.clear();

        int i = memberRepository.bulkUpdateAge(5);

        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            log.info("member = {}", member);
        }
    }

    @Test
    void entityGraphTest() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberB", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberC", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberD", 20, new Address("Seoul", "Sadang"), teamA));
        memberRepository.save(new Member("memberE", 20, new Address("Seoul", "Sadang"), teamA));

        em.flush();
        em.clear();


        // Member memberA = memberRepository.findReadOnlyByMemberName("memberA");
        // List<Member> memberA = memberRepository.findLockByMemberName("memberA");
        // log.info("memberA = {}", memberA);

        List<Member> lockByAge = memberRepository.findLockByAge(20);
        for (Member member : lockByAge) {
            log.info("member = {}", member);
        }



    }

}