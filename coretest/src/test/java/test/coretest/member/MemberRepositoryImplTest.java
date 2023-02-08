package test.coretest.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberRepositoryImplTest {

    MemberRepository repository = new MemberRepositoryImpl();

    @Test
    void save() {
        MemberVo memberVo = new MemberVo(1L, "testA", Grade.VIP);

        repository.save(memberVo);

        MemberVo result = repository.findById(1L);

        Assertions.assertThat(result.getName()).isEqualTo(memberVo.getName());

    }
}