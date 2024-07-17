package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Field에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {
    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {

        long startTime = System.currentTimeMillis();
        // 비즈니스 로직실행
        log.info("비즈니스 로직 V1 실행");
        strategy.call();

        // 비즈니스 로직 종료
        long endTime    = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime + " ms");


    }
}
