package hello.springmvctest.basic.requestmapping;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId) {
        log.info("mappingPath userId = {}", userId);
        return userId;
    }

    @GetMapping("/mapping/users/{userId}/order/{orderId}")
    public String mappingPathV2(@PathVariable("userId") String userId, @PathVariable("orderId") int orderId) {

        log.info("mappingPath userId = {}, userId = {}", userId, orderId);

        return userId + ", " + orderId;
    }

    /**
     * http://localhost:8080/mapping-param?mode=debug
     *
     * @return
     */
    @GetMapping(value = "/mapping-param", params = "mode=!debug")
    public String mappingParam() {

        return "ok";
    }

    /**
     * postman으로 테스트
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        return "ok";
    }


    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        return "ok";
    }


}
