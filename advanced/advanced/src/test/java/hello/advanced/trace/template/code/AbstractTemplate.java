package hello.advanced.trace.template.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        call(); // 상속
        // 비즈니스 로직 종료
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("totalTime = {}", totalTime + " ms");
    }

    protected abstract void call();

}
