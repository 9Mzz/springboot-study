package test.testspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.MemberVo;
import test.testspring.repository.MemoryMemberRepository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void clearStore() {
        repository.clearStore();
    }

    @Test
    public void save() {

        MemberVo member1 = new MemberVo();
        member1.setName("spring1");
        repository.save(member1);

        MemberVo member2 = new MemberVo();
        member2.setName("spring2");
        repository.save(member2);

        MemberVo result = repository.findbyName("spring1").get();

        Assertions.assertThat(member1).isEqualTo(result);


    }

    @Test
    public void findAll(){

        MemberVo member1 = new MemberVo();
        member1.setName("spring1");
        repository.save(member1);

        MemberVo member2 = new MemberVo();
        member2.setName("spring2");
        repository.save(member2);

        List<MemberVo> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}
