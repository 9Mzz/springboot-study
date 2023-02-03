package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository;


    @BeforeEach
    public void beForeAct() {
        repository = new MemoryMemberRepository();
    }

    @AfterEach
    public void afterAct() {
        repository.clearStore();
    }

    @Test
    void save() {

        Member member1 = new Member();
        member1.setName("spring");
        Member result = repository.save(member1);

        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    void findById() {

        Member member1 = new Member();
        member1.setName("spring");
        Member result = repository.save(member1);

        Member result2 = repository.findById(result.getId()).get();

        Assertions.assertThat(member1).isEqualTo(result2);

    }

    @Test
    void findByName() {

        Member member1 = new Member();
        member1.setName("spring");
        Member result = repository.save(member1);

        Member result2 = repository.findByName("spring").get();

        Assertions.assertThat(member1.getName()).isEqualTo(result2.getName());
    }

    @Test
    void findByAll() {

        Member member1 = new Member();
        member1.setName("spring");
        Member result = repository.save(member1);

        List<Member> result2 = repository.findAll();

        Assertions.assertThat(result2.size()).isEqualTo(1);
    }
}