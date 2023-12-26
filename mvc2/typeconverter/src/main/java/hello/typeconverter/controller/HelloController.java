package hello.typeconverter.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestController
public class HelloController {

  @GetMapping("/hello-v1")
  public String helloV1(HttpServletRequest request) {
    //문자 타입으로 조회
    String data = request.getParameter("data");
    //숫자 타입으로 변경
    Integer intValue = Integer.valueOf(data);

    System.out.println("intValue = " + intValue);
    return "ok";
  }

  @GetMapping("/hello-v2")
  public String helloV2(@RequestParam("data") Integer data) {
    System.out.println("data = " + data);
    System.out.println("type = " + data.getClass()
        .getName());

    return "ok";
  }


}
