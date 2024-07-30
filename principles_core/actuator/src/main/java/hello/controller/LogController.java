package hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @GetMapping("/log")
    public String log() {

        /**
         * http://localhost:8080/actuator/loggers/hello.controller
         *
         * {
         *     "configuredLevel":"TRACE"
         * }
         *
         */
        log.trace("trace log");
        log.debug("trace log");
        log.info("trace log");
        log.warn("trace log");
        log.error("trace log");
        return "ok";
    }

}
