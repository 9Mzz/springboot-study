package hello.login.web.interceptor;

import hello.login.domain.login.SessionConst;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String      requestURI = request.getRequestURI();
    HttpSession session    = request.getSession();
    log.info("로그인 체크 동작 = [{}][{}][{}]", session, requestURI, handler);

    if (session == null || session.getAttribute(SessionConst.SESSION_NAME) == null) {
      response.sendRedirect("/login?requestURL=" + requestURI);
      return false;
    }

    return true;
  }


  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
log.info("로그인 체크 동작 종료");
  }
}
