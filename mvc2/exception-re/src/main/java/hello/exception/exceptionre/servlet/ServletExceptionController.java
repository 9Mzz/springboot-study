package hello.exception.exceptionre.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ServletExceptionController {


  @GetMapping("/error-ex")
  public void errorEx(HttpServletRequest request, HttpServletResponse response) {
    throw new RuntimeException("런타임 오류 발생");
  }

  @GetMapping("/error-404")
  public void error404(HttpServletResponse response) throws IOException {
    response.sendError(404, "404 오류 발생");
  }

  @GetMapping("/error-500")
  public void error500(HttpServletResponse response) throws IOException {
    response.sendError(500, "500 오류 발생");
  }



}
