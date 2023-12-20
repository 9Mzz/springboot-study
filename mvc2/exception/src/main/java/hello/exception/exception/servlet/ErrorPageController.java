package hello.exception.exception.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorPageController {

  public static final String ERROR_EXCEPTION      = "jakarta.servlet.error.exception";
  public static final String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
  public static final String ERROR_MESSAGE        = "jakarta.servlet.error.message";
  public static final String ERROR_REQUEST_URI    = "jakarta.servlet.error.request_uri";
  public static final String ERROR_SERVLET_NAME   = "jakarta.servlet.error.servlet_name";
  public static final String ERROR_STATUS_CODE    = "jakarta.servlet.error.status_code";

  @RequestMapping("/error-page/404")
  public String error404(HttpServletRequest request) {
    log.info("errorPage 404");
    errorCheck(request);
    return "error-page/404";
  }

  @RequestMapping("/error-page/500")
  public String error500(HttpServletRequest request) {
    log.info("errorPage 500");
    errorCheck(request);
    return "error-page/500";
  }

  // produces = MediaType.APPLICATION_JSON_VALUE) = Type에 따라서 어떻게 호출될지 정함
  @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> errorPage500Api(HttpServletRequest request,
      HttpServletResponse response) {
    log.info("Api ErrorPage 500");
    Map<String, Object> result = new HashMap<>();
    Exception           ex     = (Exception) request.getAttribute(ERROR_EXCEPTION);
    result.put("status", request.getAttribute(ERROR_STATUS_CODE));
    result.put("message", ex.getMessage());

    //RequestDispatcher 안에 위의 상수들(ERROR_STATUS_CODE 등)이 다 정의돼있음.
    Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    return new ResponseEntity<>(result, HttpStatusCode.valueOf(statusCode));
  }


  public void errorCheck(HttpServletRequest request) {
    log.info("ERROR_EXCEPTION: {}", request.getAttribute(ERROR_EXCEPTION));
    log.info("ERROR_EXCEPTION_TYPE: {}", request.getAttribute(ERROR_EXCEPTION_TYPE));
    log.info("ERROR_MESSAGE: {}", request.getAttribute(ERROR_MESSAGE));
    //ex의 경우 NestedServletException 스프링이 한번 감싸서 반환
    log.info("ERROR_REQUEST_URI: {}", request.getAttribute(ERROR_REQUEST_URI));
    log.info("ERROR_SERVLET_NAME: {}", request.getAttribute(ERROR_SERVLET_NAME));
    log.info("ERROR_STATUS_CODE: {}", request.getAttribute(ERROR_STATUS_CODE));
    log.info("dispatchType={}", request.getDispatcherType());
  }

}
