package hello.login.web.filter;

import hello.login.web.SessionConst;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginCheckFilter implements Filter {

  private static final String[] whiteList = {"/", "/members/add", "/login", "logout", "/css/*"};

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String             requestURI         = httpServletRequest.getRequestURI();

    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    try {
      log.info("인증 체크 필터 = {}", requestURI);

      if (isLoginCheckPath(requestURI)) {
        log.info("인증 체크 로직 실행 {}", requestURI);
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
          log.info("미인증 사용자 요청 {}", requestURI);
          httpServletResponse.sendRedirect("/login?redirectURL=" + requestURI);
          return;
        }
      }
      chain.doFilter(request, response);
    } catch (Exception e) {
      throw e;  //예외 로그 가능, 톰캣까지 예외를 보내주어야 한다.
    } finally {
      log.info("인증 체크 필터 종료");
    }

  }

  /**
   * 화이트 리스트의 경우 인증 체크 x
   */
  private boolean isLoginCheckPath(String requestURI) {
    return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
  }


}
