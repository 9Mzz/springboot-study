package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import test.testspring.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeAct() {
        repository = new MemoryMemberRepository();
    }

    @AfterEach
    void storeClear() {
        repository.storeClear();
    }

    @DisplayName("회원가입")
    @Test
    void save() {
        Member test1 = new Member();
        test1.setName("spring1");
        repository.save(test1);

        Member test2 = new Member();
        test2.setName("spring2");
        repository.save(test2);

        Member result = repository.findById(test1.getId()).get();

        Assertions.assertThat(test1).isEqualTo(result);

    }


    @DisplayName("이름으로 검색")
    @Test
    void findByName() {
        Member test1 = new Member();
        test1.setName("spring1");
        repository.save(test1);

        Member test2 = new Member();
        test2.setName("spring2");
        repository.save(test2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(test1).isEqualTo(result);

    }

    @DisplayName("모든 회원 검색")
    @Test
    void findAll() {
        Member test1 = new Member();
        test1.setName("spring1");
        repository.save(test1);

        Member test2 = new Member();
        test2.setName("spring2");
        repository.save(test2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}