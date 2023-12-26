package hello.typeconverter.controller;

import ch.qos.logback.classic.Logger;
import hello.typeconverter.type.IpPort;
import javax.sound.sampled.Port;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

  @Override
  public String convert(IpPort source) {

    log.info("convert source = {}", source);
    //IpPort 객체 -> "127.0.0.1:8080"
    return source.getIp() + ":" + source.getPort();
  }
}
