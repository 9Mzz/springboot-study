package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecoratorTest() {
        RealComponent          component = new RealComponent();
        DecoratorPatternClient client    = new DecoratorPatternClient(component);
        client.execute();
    }

    @Test
    void DecoratorTestV1() {
        Component              realComponent    = new RealComponent();
        Component              messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client           = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void DecoratorTestV2() {

        RealComponent          realComponent = new RealComponent();
        TimeDecorator          timeDecorator = new TimeDecorator(realComponent);
        DecoratorPatternClient client        = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }


}
