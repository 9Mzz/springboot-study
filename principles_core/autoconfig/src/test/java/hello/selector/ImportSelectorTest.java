package hello.selector;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class ImportSelectorTest {


    /**
     * 정적 Config
     */
    @Test
    void staticConfig() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(StaticConfig.class);
        HelloBean                          bean       = appContext.getBean(HelloBean.class);
        Assertions.assertThat(bean)
                .isNotNull();
    }

    @Import(HelloConfig.class)
    @Configuration
    public static class StaticConfig {
    }

    /**
     * ImportSelector 을 사용한 정적 Config
     */
    @Test
    void selectorConfig() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(SelectorConfig.class);
        HelloBean                          bean       = appContext.getBean(HelloBean.class);
        Assertions.assertThat(bean)
                .isNotNull();
    }

    @Import(HelloImportSelector.class)
    @Configuration
    public static class SelectorConfig {
    }

}
