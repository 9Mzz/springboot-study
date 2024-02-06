package hello.toyex.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
//@RestControllerAdvice
public class ErrorController {


//    @ExceptionHandler
    public ResponseEntity<ErrorCode> IllegalStateEx(IllegalStateException e) {
        log.info("[IllegalStateException] ex", e.getMessage());
        ErrorCode errorCode = new ErrorCode("IllegalStateException", e.getMessage());
        return new ResponseEntity<>(errorCode, HttpStatus.BAD_REQUEST);
    }


}
