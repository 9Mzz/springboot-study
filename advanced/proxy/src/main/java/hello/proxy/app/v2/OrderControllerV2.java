package hello.proxy.app.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// @RequestMapping -> 스프링 부트 3.0 인식 불가
@RestController
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;

    public OrderControllerV2(OrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v2/request")
    public String request(String itemId) {
        log.info("itemId: {}", itemId);
        orderService.orderItem(itemId);
        return itemId;
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
