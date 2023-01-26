package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;
import test.testspring.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void clearStore() {
        repository.clearStore();
    }

    @DisplayName("데이터 저장")
    @Test
    void save() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.findbyId(member1.getId()).get();

        Assertions.assertThat(member1).isEqualTo(result);
    }


    @DisplayName("이름으로 검색")
    @Test
    void findbyName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.findbyName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);

    }

    @DisplayName("회원 모두 검색 테스트")
    @Test
    void findAll() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);


    }
}