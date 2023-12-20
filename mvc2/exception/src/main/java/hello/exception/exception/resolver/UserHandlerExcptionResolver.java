package hello.exception.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class UserHandlerExcptionResolver implements HandlerExceptionResolver {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {

    try {
      if (ex instanceof UserException) {
        log.info("UserException resolver to 400");
        String acceptHeader = request.getHeader("accept");
        log.info("acceptHeader : {}", acceptHeader);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        if ("applilcation/json".equals(acceptHeader)) {
          Map<String, Object> errorResult = new HashMap<>();
          errorResult.put("ex", ex.getClass());
          errorResult.put("message", ex.getMessage());

          String result = objectMapper.writeValueAsString(errorResult);
          log.info("objectMapper : {} ", result);

          response.setContentType("application/json");
          response.setCharacterEncoding("utf-8");
          response.getWriter()
              .write(result);
          return new ModelAndView();
        } else {
          //TEXT/HTML
          return new ModelAndView("error/500");
        }

      }


    } catch (Exception e) {
      log.error("resolver ex", e);
    }

    return null;
  }
}
