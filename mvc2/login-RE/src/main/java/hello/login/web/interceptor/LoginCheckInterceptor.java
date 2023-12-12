package hello.login.web.interceptor;


import hello.login.web.SessionConst;
import java.nio.file.FileAlreadyExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String      requestURI = request.getRequestURI();
    HttpSession session    = request.getSession();
    if (session == null || session.getAttribute(SessionConst.SESSION_MEMBER) == null) {
      response.sendRedirect("/login?requestURI=" + requestURI);
      return false;
    }
    return true;
  }


}
