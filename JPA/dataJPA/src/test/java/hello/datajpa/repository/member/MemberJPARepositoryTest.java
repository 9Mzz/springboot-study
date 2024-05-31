package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberJPARepositoryTest {

    @Autowired
    MemberJPARepository repository;


    @Test
    @DisplayName("JPA 기반 테스트")
    void crudTest() {
        Member memberA    = new Member("memberA");
        Member saveMember = repository.save(memberA);
        Member findMember = repository.findById(saveMember.getId())
                .get();

        assertThat(saveMember).isEqualTo(findMember);
        assertThat(saveMember.getId()).isEqualTo(findMember.getId());
        assertThat(saveMember.getUserName()).isEqualTo(findMember.getUserName());

        log.info("memberA.getCreateBy() = {}", memberA.getCreateBy());
        log.info(" memberA.getModifyBy() = {}", memberA.getModifyBy());
    }

    @Test
    void basicCrud() {
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");
        repository.save(memberA);
        repository.save(memberB);

        Member findMemberA = repository.findById(memberA.getId())
                .get();
        Member findMemberB = repository.findById(memberB.getId())
                .get();

        assertThat(memberA).isEqualTo(findMemberA);
        assertThat(memberB).isEqualTo(findMemberB);

        findMemberA.setUserName("memberAAAAAAAAAAAAAAA");

        // 리스트 조회 검증
        List<Member> all = repository.findAll();
        assertThat(all.size()).isEqualTo(2);
        // Count 검증
        assertThat(repository.getCount()).isEqualTo(2);
        // Delete 검증
        repository.delete(findMemberA);
        repository.delete(findMemberB);
        assertThat(repository.getCount()).isEqualTo(0);


    }

    @Test
    void findByUsernameAgeGreaterThen() {
        Member memberA = new Member("memberA", 10);
        Member memberB = new Member("memberA", 20);
        repository.save(memberA);
        repository.save(memberB);

        List<Member> result = repository.findByUsernameAgeGreaterThen("memberA", 5);
        for (Member member : result) {
            log.info("member = {}", member);
        }

    }

    @Test
    void findByPage() {
        Member memberA = new Member("memberA", 20);
        Member memberB = new Member("memberB", 20);
        Member memberC = new Member("memberC", 20);
        Member memberD = new Member("memberD", 20);

        repository.save(memberA);
        repository.save(memberB);
        repository.save(memberC);
        repository.save(memberD);

        int age    = 20;
        int offset = 0;
        int limit  = 3;

        List<Member> members = repository.findByPage(age, offset, limit);
        for (Member member : members) {
            log.info("member = {}", member);
        }

        Long count = repository.getCount(10);
        log.info("count = {}", count);

    }

    @Test
    void bulkAgePlus() {
        repository.save(new Member("memberA", 20));
        repository.save(new Member("memberB", 20));
        repository.save(new Member("memberC", 20));
        repository.save(new Member("memberD", 20));
        repository.save(new Member("memberE", 10));
        int i = repository.bulkAgePlus(20);
        log.info("i = {}", i);

        List<Member> memberList = repository.findAll();
        for (Member member : memberList) {
            log.info("member = {}", member);
        }

    }
}