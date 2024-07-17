package hello.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {


    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();
        // 로직 시작
        callback.call();
        // 로직 끝
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("total time: {} ms", totalTime);


    }

}
