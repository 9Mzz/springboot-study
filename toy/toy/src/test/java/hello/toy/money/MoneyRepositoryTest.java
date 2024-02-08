package hello.toy.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootTest
class MoneyRepositoryTest {

    @Autowired
    MoneyRepository moneyRepository;
    @Autowired
    MoneyService    moneyService;

    private static final String MEMBER_A = "MEMBER_A";
    private static final String MEMBER_B = "MEMBER_B";
    private static final String MEMBER_C = "ex";

    @TestConfiguration
    static class testConfig {

        private final DataSource dataSource;

        testConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }


        @Bean
        public MoneyRepository moneyRepository() {
            return new MoneyRepository(dataSource);
        }
    }

    @BeforeEach
    void before() {
        moneyRepository.delete(MEMBER_A);
        moneyRepository.delete(MEMBER_B);
        moneyRepository.delete(MEMBER_C);
    }

    @Test
    void save() {
        Member member = new Member(MEMBER_A, 10000);
        moneyRepository.save(member);

        //
        Member findMember = moneyRepository.findById(MEMBER_A);
        Assertions.assertThat(member)
                .isEqualTo(findMember);
        //
        moneyRepository.update(MEMBER_A, 50000);
        Member updateMember = moneyRepository.findById(MEMBER_A);
        Assertions.assertThat(updateMember.getMoney())
                .isEqualTo(50000);

    }

    @Test
    void transferTest() {
        Member memberA = new Member(MEMBER_A, 50000);
        Member memberB = new Member(MEMBER_B, 50000);
        moneyRepository.save(memberA);
        moneyRepository.save(memberB);

        moneyService.bizLogic(memberA.getMember_id(), memberB.getMember_id(), 3000);
    }

    @Test
    void transferValidation() {
        Member memberA = new Member(MEMBER_A, 50000);
        Member memberC = new Member(MEMBER_C, 50000);
        moneyRepository.save(memberA);
        moneyRepository.save(memberC);

        Assertions.assertThatThrownBy(() -> {
                    moneyService.bizLogic(memberA.getMember_id(), memberC.getMember_id(), 3000);
                })
                .isInstanceOf(IllegalStateException.class);


    }
}