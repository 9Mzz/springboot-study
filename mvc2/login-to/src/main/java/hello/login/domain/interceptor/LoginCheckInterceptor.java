package hello.login.domain.interceptor;

import hello.login.domain.login.SessionConst;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session    = request.getSession();
    String      requestURI = request.getRequestURI();

    log.info("preHandle 호출");

    if (session == null || session.getAttribute(SessionConst.SESSION_NAME) == null) {
      response.sendRedirect("/login?URL=" + requestURI);
      return false;
    }

    return true;
  }


}
