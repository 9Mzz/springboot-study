package hello.proxy.commonv2.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceInterfaceV2Impl implements ServiceInterfaceV2 {
    @Override
    public void save() {
        log.info("save 호출");

    }

    @Override
    public void find() {
        log.info("find 호출");
    }
}
