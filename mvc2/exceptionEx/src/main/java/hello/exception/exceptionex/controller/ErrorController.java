package hello.exception.exceptionex.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ErrorController {


  @GetMapping("/api/error/{id}")
  public MemberDto errorController(@PathVariable("id") String id) {

    if (id.equals("ex")) {
      throw new RuntimeException("런타임 오류");
    }
    if (id.equals("bad")) {
      throw new IllegalArgumentException("잘못된 입력 값");
    }

    return new MemberDto(id, "hello " + id);
  }


  @Data
  @AllArgsConstructor
  static class MemberDto {

    private String id;
    private String addId;
  }


}
