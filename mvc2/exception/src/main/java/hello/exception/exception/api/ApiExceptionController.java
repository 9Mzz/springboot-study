package hello.exception.exception.api;

import hello.exception.exception.exception.UserException;
import jdk.jfr.DataAmount;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {

  @GetMapping("/api/members/{id}")
  public MemberDto getMember(@PathVariable("id") String id) {
    log.info("id = {}", id);
    if (id.equals("ex")) {
      throw new RuntimeException("런타임 오류 발생, 잘못된 사용자");
    }
    if (id.equals("bad")) {
      throw new IllegalArgumentException("잘못된 사용자");
    }
    if (id.equals("user-ex")) {
      throw new UserException("사용자 오류");
    }

    return new MemberDto(id, "hello " + id);
  }


  @Data
  @AllArgsConstructor
  static class MemberDto {

    private String memberId;
    private String name;
  }

}
