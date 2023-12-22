package hello.exception.exception.exhandler.advice;

import hello.exception.exception.exception.UserException;
import hello.exception.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
대상 컨트롤러 지정 방법

// Target all Controllers annotated with @RestController
@ControllerAdvice(annotations = RestController.class)
public class ExampleAdvice1 {}
// Target all Controllers within specific packages
@ControllerAdvice("org.example.controllers")
public class ExampleAdvice2 {}
// Target all Controllers assignable to specific classes
@ControllerAdvice(assignableTypes = {ControllerInterface.class,
    AbstractController.class})
public class ExampleAdvice3 {}
*/

@Slf4j
@RestControllerAdvice()
public class ExControllerAdvice {

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

}
