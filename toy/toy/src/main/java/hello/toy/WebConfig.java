package hello.toy;

import hello.toy.converter.IpPortToStringConverter;
import hello.toy.converter.NumberFormatter;
import hello.toy.converter.StringToIpPortConverter;
import hello.toy.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/css/**", "/*.ico", "/login", "/logout", "/members/**", "/file/**", "/attach/**", "/images/**", "/toy/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new IpPortToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addFormatter(new NumberFormatter());
    }
}
