package hello.aop.examv2;

import hello.aop.examv2.annotationv2.TraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceV2 {

    private final ExamRepositoryV2 examRepositoryV2;

    @TraceV2
    public void save(String itemId) {
        examRepositoryV2.saveItem(itemId);
    }

}
