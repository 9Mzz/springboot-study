package hello.login.web.error;

import ch.qos.logback.core.joran.conditional.IfAction;
import java.io.PipedReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ErrorController {

  @RequestMapping("/api/{id}")
  public MemberDto errorController(@PathVariable("id") String id) {

    if (id.equals("ex")) {
      throw new RuntimeException("런타임 오류");
    }
    if (id.equals("bad")) {
      throw new IllegalArgumentException("사용자 오류");
    }


    return new MemberDto(id, "hello " + id);
  }

  @Data
  @AllArgsConstructor
  static class MemberDto {

    private String id;
    private String message;
  }

}
