package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    private final MemoryMemberRepository repository = new MemoryMemberRepository();


    @BeforeEach
    public void beforeAct() {
        repository.clearStore();
    }

    @DisplayName("저장 테스트 ID로 검색")
    @Test
    void save() {

        Member test1 = new Member();
        test1.setName("spring");
        repository.save(test1);

        Member result = repository.findById(test1.getId()).get();

        Assertions.assertThat(test1).isEqualTo(result);
    }


    @DisplayName("이름으로 검색")
    @Test
    void findByName() {
        Member test1 = new Member();
        test1.setName("spring");
        repository.save(test1);

        Member result = repository.findByName("spring").get();


        Assertions.assertThat(test1).isEqualTo(result);

    }

    @Test
    void findAll() {
        Member test1 = new Member();
        test1.setName("spring");
        repository.save(test1);

        Member test2 = new Member();
        test2.setName("spring2");
        repository.save(test2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}