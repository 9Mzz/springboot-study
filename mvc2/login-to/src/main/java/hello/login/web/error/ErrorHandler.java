package hello.login.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

  @ResponseStatus(code = HttpStatus.ACCEPTED)
  @ExceptionHandler
  public ErrorResult RuntimeError(RuntimeException e) {
    log.error("[RuntimeException] ", e);
    return new ErrorResult("ex", e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler
  public ErrorResult IllegalError(IllegalArgumentException e) {
    log.error("[IllegalArgumentException] e", e);
    return new ErrorResult("bad", e.getMessage());
  }


}
