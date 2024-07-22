package hello.proxy.commonv2.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteServiceV2 {
    public void save() {
        log.info("concreteServiceV2 save 호출");
    }
}
