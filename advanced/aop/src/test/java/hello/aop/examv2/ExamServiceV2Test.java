package hello.aop.examv2;

import hello.aop.examv2.aopv2.RetryAspectV2;
import hello.aop.examv2.aopv2.TraceAspectV2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({RetryAspectV2.class, TraceAspectV2.class})
@SpringBootTest
class ExamServiceV2Test {

    @Autowired
    ExamServiceV2 examServiceV2;


    @Test
    void testV1() {
    }

    @Test
    void save() {
        for (int i = 1; i <= 10; i++) {
            examServiceV2.save("item");
        }

    }
}