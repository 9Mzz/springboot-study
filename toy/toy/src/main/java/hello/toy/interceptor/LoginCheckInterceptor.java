package hello.toy.interceptor;

import hello.toy.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session    = request.getSession();
        String      requestURI = request.getRequestURI();
        if(session == null || session.getAttribute(SessionConst.SESSION_NAME) == null) {
            response.sendRedirect("/login?requestURL=" + requestURI);
            return false;
        }
        return true;
    }
}
