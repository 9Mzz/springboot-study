package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import hello.datajpa.domain.MemberDto;
import hello.datajpa.domain.Team;
import hello.datajpa.repository.team.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    TeamRepository   teamRepository;
    @Autowired
    EntityManager    em;

    @Test
    void testMember() {
        Member memberA    = new Member("memberA");
        Member saveMember = memberRepository.save(memberA);
        Member findMember = memberRepository.findById(saveMember.getId())
                .get();
        assertThat(saveMember).isEqualTo(findMember);
        assertThat(saveMember.getId()).isEqualTo(findMember.getId());
        assertThat(saveMember.getUserName()).isEqualTo(findMember.getUserName());

        log.info("memberA.getCreateBy() = {}", memberA.getCreateBy());
        log.info(" memberA.getModifyBy() = {}", memberA.getModifyBy());
    }

    @Test
    void basicCrud() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId())
                .get();
        Member findMember2 = memberRepository.findById(member2.getId())
                .get();

        assertThat(member1).isEqualTo(findMember1);
        assertThat(member2).isEqualTo(findMember2);

        findMember1.setUserName("memberAAAAAAAAAAAAAAA");

        // 리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // Count 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // Delete 검증
        memberRepository.delete(findMember1);
        memberRepository.delete(findMember2);

        long deleteCount = memberRepository.count();
        assertThat(deleteCount).isZero();

    }

    @Test
    void findByUserNameAndAgeGreaterThan() {
        Member memberA = new Member("memberA", 10);
        Member memberB = new Member("memberA", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> result = memberRepository.findByUserNameAndAgeGreaterThan("memberA", 5);
        for (Member member : result) {
            log.info("member = {}", member);
        }

    }

    /**
     * Repository method에 쿼리 정의
     */
    @Test
    void findUser() {
        Member memberA = new Member("memberA", 10);
        Member memberB = new Member("memberA", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> findMembers = memberRepository.findUser("memberA", 10);
        for (Member findMember : findMembers) {
            log.info("findMember = {}", findMember);
        }
    }


    @Test
    void findUserNameList() {
        Member memberA = new Member("memberA", 10);
        Member memberB = new Member("memberA", 20);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<String> userNameList = memberRepository.findUserNameList();
        for (String s : userNameList) {
            log.info("userNameList = {}", s);
        }

    }

    @Test
    void findMemberDto() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        Member memberA = new Member("memberA", 10, teamA);
        Member memberB = new Member("memberA", 20, teamA);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        List<MemberDto> memberDto = memberRepository.findMemberByDto();

        for (MemberDto dto : memberDto) {
            log.info("dto = {}", dto);
        }

    }

    @DisplayName("컬렉션 파라미터 바인딩")
    @Test
    void findByNames() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        Member memberA = new Member("memberA", 10, teamA);
        Member memberB = new Member("memberB", 20, teamA);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> memberList = memberRepository.findByNames(Arrays.asList("memberA", "memberB"));
        for (Member member : memberList) {
            log.info("member = {}", member);
        }
    }

    @DisplayName("페이징과 정렬 사용")
    @Test
    void findByUserName() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));

        String memberName = "memberA";
        int    offset     = 0;
        int    limit      = 3;

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "userName"));

        Page<Member> memberPage = memberRepository.findByUserName(memberName, pageRequest);
        List<Member> memberList = memberPage.getContent();
        for (Member member : memberList) {
            log.info("member = {}", member);
        }
    }

    @Test
    void findByAge() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));

        int age    = 10;
        int offset = 0;
        int limit  = 3;

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "userName"));

        Page<Member> memberPage  = memberRepository.findByAge(age, pageRequest);
        List<Member> pageContent = memberPage.getContent();
        for (Member member : pageContent) {
            log.info("member = {}", member);
        }
    }

    @Test
    void Paging() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));

        int age    = 20;
        int offset = 0;
        int limit  = 3;

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "userName"));

        Page<Member> memberPage = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> dtoPage = memberPage.map(member -> new MemberDto(member.getId(), member.getUserName(), member.getAge(), member.getTeam()
                .getName()));
        List<MemberDto> content = dtoPage.getContent();
        for (MemberDto memberDto : content) {
            log.info("memberDto = {}", memberDto);
        }

    }

    @Test
    void bulkAgePlus() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));

        // Logic
        int i = memberRepository.bulkAgePlus(20);
        log.info("i = {}", i);

        // 영속성 컨테스트에서 분리를 해 줘야 함 -> 과거 값이 남아있음
        // clearAutomatically = true
        // em.flush();
        // em.clear();

        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            log.info("member = {}", member);
        }

    }

    @Test
    void findMemberFetchjoin() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));

        // Logic
        List<Member> memberList = memberRepository.findMemberFetchjoin();

        for (Member member : memberList) {
            log.info("member = {}", member);
        }
    }

    @Test
    void findMemberEntityGraph() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));

        // Logic
        // List<Member> members = memberRepository.findMemberEntityGraph();
        List<Member> members = memberRepository.findMemberByUserNameEntityGraph("memberA");
        for (Member member : members) {
            log.info("member = {}", member);
        }
    }

    @Test
    void findQueryHintByUserName() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));

        em.flush();
        em.clear();
        Member memberA = memberRepository.findQueryHintByUserName("memberA");
        memberA.setUserName("memberBBBB");
        em.flush();
    }

    @Test
    void findLockByUserName() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA", 10, teamA));

        Member memberA = memberRepository.findLockByUserName("memberA");

    }
}