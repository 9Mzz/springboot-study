package hello.aop.examv2;


import hello.aop.examv2.annotationv2.RetryV2;
import hello.aop.examv2.annotationv2.TraceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ExamRepositoryV2 {

    private static int seq = 0;

    @RetryV2
    @TraceV2
    public String saveItem(String itemId) {
        seq++;
        log.info("[Repository] {}", itemId + seq);
        if (seq % 5 == 0) {
            throw new IllegalStateException("오류 발생");
        }
        return itemId;
    }

}
