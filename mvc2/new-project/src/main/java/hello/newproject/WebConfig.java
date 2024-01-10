package hello.newproject;

import hello.newproject.converter.IpPortToStringConverter;
import hello.newproject.converter.NumberFormatter;
import hello.newproject.converter.StringToIpPortConverter;
import hello.newproject.interceptor.LoginCheckInterCeptor;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheckInterCeptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/*.ico", "/error", "/css/**", "/login", "logout", "members/add", "/play/**", "/format/**");
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    // String <-> ipPort
    registry.addConverter(new IpPortToStringConverter());
    registry.addConverter(new StringToIpPortConverter());

    registry.addFormatter(new NumberFormatter());


  }
}
