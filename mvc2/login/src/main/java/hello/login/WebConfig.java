package hello.login;

import hello.login.web.filter.LogFilter;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

  @Bean
  public FilterRegistrationBean logFilter() {
    FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
    filter.setFilter(new LogFilter());
    filter.setOrder(1);
    filter.addUrlPatterns("/*");

    return filter;
  }

}
