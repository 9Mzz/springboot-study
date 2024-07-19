package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternClient {

    private final Component component;

    public DecoratorPatternClient(Component component) {
        this.component = component;
    }

    public String execute() {
        String result = component.operation();
        // data ->  "**********data**********
        log.info("result: {}", result);
        String decoResult = "**********" + result + "**********";
        log.info("messageDecorator 꾸미기 적용 전 = {}, 후 = {}", result, decoResult);
        return decoResult;
    }

}
