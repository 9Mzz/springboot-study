package test.testspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import test.testspring.domain.MemberVo;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {

        MemberVo member1 = new MemberVo();
        member1.setName("spring1");
        repository.save(member1);

        MemberVo member2 = new MemberVo();
        member2.setName("spring2");
        repository.save(member2);


        MemberVo member3 = new MemberVo();
        member3.setName("spring3");
        repository.save(member3);

        List<MemberVo> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(3);

    }

    @Test
    public void findName() {

        MemberVo member1 = new MemberVo();
        member1.setName("spring1");
        repository.save(member1);

        MemberVo member2 = new MemberVo();
        member2.setName("spring2");
        repository.save(member2);


        MemberVo member3 = new MemberVo();
        member3.setName("spring3");
        repository.save(member3);

        MemberVo result = repository.findbyName("spring3").get();

        Assertions.assertThat(member3).isEqualTo(result);

    }

}
