package hello.typeconverter.converter;

import hello.typeconverter.controller.IpPortToStringConverter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.scheduling.support.SimpleTriggerContext;

public class ConversionServiceTest {


  @Test
  void conversionService() {
    //등록
    DefaultConversionService conversionService = new DefaultConversionService();
    conversionService.addConverter(new StringToIntegerConverter());
    conversionService.addConverter(new IntegerToStringConverter());
    conversionService.addConverter(new IpPortToStringConverter());
    conversionService.addConverter(new StringToIpPortConverter());

    //사용 -> 반환할 객체, 반환하고 싶은 타입
//    Integer result = conversionService.convert("10", Integer.class);
    // StringToIntegerConverter 작동됨
//    System.out.println("result = " + result);
    //타입 검증
//    System.out.println("type = " + result.getClass()
//        .getName());
    //assertions
    Assertions.assertThat(conversionService.convert("10", Integer.class))
        .isEqualTo(10);
    Assertions.assertThat(10)
        .isEqualTo(conversionService.convert("10", Integer.class));

    //String -> ipPort Test
    IpPort stringIpPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
    Assertions.assertThat(stringIpPort)
        .isEqualTo(new IpPort("127.0.0.1", 8080));

    //ipPort -> String Test
    String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
    Assertions.assertThat(ipPortString)
        .isEqualTo("127.0.0.1:8080");


  }

}
