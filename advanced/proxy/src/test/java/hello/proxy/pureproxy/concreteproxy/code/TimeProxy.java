package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.nanoTime();
        // 로직 실행
        String result = concreteLogic.operation();
        // 로직 완료
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTime = {}", totalTime);


        return result;
    }
}
