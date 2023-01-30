package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import test.testspring.domain.MemberVo;

import java.lang.reflect.Member;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @BeforeEach
    public void clearStore() {
        repository.clearStore();
    }

    @Test
    void save() {

        MemberVo test1 = new MemberVo();
        test1.setName("test1");
        repository.save(test1);

        MemberVo test2 = new MemberVo();
        test2.setName("test2");
        repository.save(test2);

        MemberVo test3 = new MemberVo();
        test3.setName("test3");
        repository.save(test3);

        MemberVo result = repository.findbyId(test1.getId()).get();

        Assertions.assertThat(test1).isEqualTo(result);
    }

    @Test
    void findbyName() {

        MemberVo test1 = new MemberVo();
        test1.setName("test1");
        repository.save(test1);

        MemberVo test2 = new MemberVo();
        test2.setName("test2");
        repository.save(test2);

        MemberVo test3 = new MemberVo();
        test3.setName("test3");
        repository.save(test3);

        MemberVo result = repository.findbyName("test1").get();

        Assertions.assertThat(test1).isEqualTo(result);

    }

    @Test
    void findAll() {

        MemberVo test1 = new MemberVo();
        test1.setName("test1");
        repository.save(test1);

        MemberVo test2 = new MemberVo();
        test2.setName("test2");
        repository.save(test2);

        MemberVo test3 = new MemberVo();
        test3.setName("test3");
        repository.save(test3);

        List<MemberVo> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(3);

    }
}