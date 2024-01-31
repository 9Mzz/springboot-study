package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@component 어노테이션이 붙은 클래스들을 찾아서 전부 등록해줌.
//(type = FilterType.ANNOTATION, classes = Configuration.class)) 하는 이유 : @configuration은 수동등록인데 자동등록하면 안됨
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {


}
