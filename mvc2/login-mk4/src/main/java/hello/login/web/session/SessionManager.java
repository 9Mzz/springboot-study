package hello.login.web.session;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionManager {

  public static final String              MY_SESSION_ID = "mySessionId";
  private static      Map<String, Object> sessionStore  = new ConcurrentHashMap<>();

  /**
   * 세션 생성
   */
  public void createSession(Object value, HttpServletResponse response) {

    String sessionId = UUID.randomUUID()
        .toString();
    sessionStore.put(sessionId, value);

    Cookie mySessionCookie = new Cookie(MY_SESSION_ID, sessionId);
    log.info("created session = {}", mySessionCookie);
    response.addCookie(mySessionCookie);
  }

  /**
   * 세션 조회
   *
   * @return
   */

  public Object getCookie(HttpServletRequest request, String cookieName) {

    Cookie mySessionCookie = findCookie(request, MY_SESSION_ID);
    if (mySessionCookie == null) {
      return null;
    }

    return sessionStore.get(mySessionCookie.getValue());
  }

  public Cookie findCookie(HttpServletRequest request, String cookieName) {

    if (request.getCookies() == null) {
      return null;
    }

    return Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName()
            .equals(cookieName))
        .findFirst()
        .orElse(null);
  }

  public void expire(HttpServletRequest request) {
    Cookie mySessionCookie = findCookie(request, MY_SESSION_ID);

    if (mySessionCookie != null) {
      sessionStore.remove(mySessionCookie.getValue());
    }
  }

}
