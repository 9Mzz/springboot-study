package hello.newproject.interceptor;

import hello.newproject.vo.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterCeptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String      requestURI = request.getRequestURI();
    HttpSession session    = request.getSession();

    if(session == null || session.getAttribute(SessionConst.SESSION_NAME) == null) {
      response.sendRedirect("/login?requestURL=" + requestURI);
      return false;
    }

    return true;
  }

}
