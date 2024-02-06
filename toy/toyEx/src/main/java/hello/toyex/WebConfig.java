package hello.toyex;

import hello.toyex.interceptor.LoginCheckInterceptor;
import hello.toyex.toy.IpPortToStringConverter;
import hello.toyex.toy.NumeberFormatter;
import hello.toyex.toy.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new IpPortToStringConverter());
        registry.addConverter(new StringToIpPortConverter());

        registry.addFormatter(new NumeberFormatter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "*.ico", "/css/**", "/login", "logout", "/members/add", "/error", "/toy/**", "/file/**", "/images/**", "/attach/**");
    }


}
