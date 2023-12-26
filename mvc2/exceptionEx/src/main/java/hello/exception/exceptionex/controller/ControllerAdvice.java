package hello.exception.exceptionex.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {


  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorResult IllegalError(IllegalArgumentException e) {
    log.error("IllegalArgumentException 발생", e);
    return new ErrorResult("BAD", e.getMessage());
  }

  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler
  public ErrorResult runtimeError(RuntimeException e) {
    log.error("IllegalArgumentException e", e);
    return new ErrorResult("ex", "내부 오류");
  }


}
