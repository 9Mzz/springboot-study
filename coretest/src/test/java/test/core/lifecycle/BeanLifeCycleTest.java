package test.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        //ConfigurableApplicationContext = AnnotationConfigApplicationContext의 상위
        ConfigurableApplicationContext ac            = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient                  networkClient = ac.getBean("networkClient", NetworkClient.class);

        ac.close();

        System.out.println("networkClient = " + networkClient);
    }


    @Configuration static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {

            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");

            return networkClient;
        }


    }

}
