package hello.exception.exception.api;

import hello.exception.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionV3Controller {

  //ExceptionHandler
/*
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorResult illigalExHandler(IllegalArgumentException e) {
    log.error("[exceptionHandler] ex", e);
    return new ErrorResult("BAD", e.getMessage());
  }

  @ExceptionHandler()
  public ResponseEntity<ErrorResult> userExHandler(UserException e) {
    log.error("[UserException] ex", e);
    ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());

    return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler
  public ErrorResult exHandler(RuntimeException e) {
    log.error("[RuntimeException] ex", e);
    return new ErrorResult("RUNTIME-ERROR", e.getMessage());
  }
*/

  @GetMapping("/api3/members/{id}")
  public MemberDto getMember(@PathVariable("id") String id) {

    if (id.equals("ex")) {
      throw new RuntimeException("잘못된 사용자");
    }
    if (id.equals("bad")) {
      throw new IllegalArgumentException("잘못된 입력 값");
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
