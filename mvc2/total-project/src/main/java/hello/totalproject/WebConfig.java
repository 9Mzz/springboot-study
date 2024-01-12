package hello.totalproject;

import hello.totalproject.converter.IpPortToStringConverter;
import hello.totalproject.converter.NumberFormatter;
import hello.totalproject.converter.StringToIpPortConverter;
import hello.totalproject.interceptor.MemberInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MemberInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/*.ico", "/css/**", "/error", "/login", "/members/add", "/logout", "/converter/**", "/formatter/**");
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    // String <-> IpPort
    registry.addConverter(new StringToIpPortConverter());
    registry.addConverter(new IpPortToStringConverter());

    registry.addFormatter(new NumberFormatter());

  }
}
