package hello.toyex.interceptor;

import hello.toyex.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("login check 작동");
        HttpSession session    = request.getSession();
        String      requestURI = request.getRequestURI();

        if(session == null || session.getAttribute(SessionConst.SESSION_NAME) == null) {
            response.sendRedirect("/login?requestURL=" + requestURI);
            return false;
        }
        return true;
    }
}
