package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.MemberVo;

import java.lang.reflect.Member;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void clearStore() {
        repository.clearStore();
    }

    @Test
    void save() {

        MemberVo test1 = new MemberVo();
        test1.setName("spring1");
        repository.save(test1);

        MemberVo test2 = new MemberVo();
        test2.setName("spring2");
        repository.save(test2);


        MemberVo result = repository.findbyId(test1.getId()).get();

        Assertions.assertThat(result).isEqualTo(test1);


    }

    @Test
    void findbyName() {
        MemberVo test1 = new MemberVo();
        test1.setName("spring1");
        repository.save(test1);

        MemberVo test2 = new MemberVo();
        test2.setName("spring2");
        repository.save(test2);

        MemberVo result = repository.findbyName("spring1").get();
        System.out.println("result = " + result);

        Assertions.assertThat(result).isEqualTo(test1);
    }

    @Test
    void findAll() {
        MemberVo test1 = new MemberVo();
        test1.setName("spring1");
        repository.save(test1);

        MemberVo test2 = new MemberVo();
        test2.setName("spring2");
        repository.save(test2);

        List<MemberVo> result = repository.findAll();
        System.out.println("result = " + result);


    }
}