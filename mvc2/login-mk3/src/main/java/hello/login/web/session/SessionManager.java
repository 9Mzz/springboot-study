package hello.login.web.session;

import java.lang.ref.ReferenceQueue;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

  public static final String              MY_SESSION_ID = "mySessionId";
  private final       Map<String, Object> sessionStore  = new ConcurrentHashMap<>();


  public void createSession(Object value, HttpServletResponse response) {

    //랜덤uid 생성
    String sessionId = UUID.randomUUID()
        .toString();
    //map에 저장
    sessionStore.put(sessionId, value);
    //response 저장
    Cookie mySessionCookie = new Cookie(MY_SESSION_ID, sessionId);
    response.addCookie(mySessionCookie);
  }

  /**
   * 세션 조회
   */
  public Object getSession(HttpServletRequest request) {

    Cookie mySessionCookie = getCookie(request, MY_SESSION_ID);
    if (mySessionCookie == null) {
      return null;
    }
    return sessionStore.get(mySessionCookie.getValue());
  }

  /**
   * 세션 제거
   */
  public void expire(HttpServletRequest request) {
    Cookie mySessionCookie = getCookie(request, MY_SESSION_ID);
    if (mySessionCookie != null) {
      sessionStore.remove(mySessionCookie.getValue());
    }
  }

  public Cookie getCookie(HttpServletRequest request, String cookieName) {
    if (request.getCookies() == null) {
      return null;
    }

    return Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName()
            .equals(cookieName))
        .findFirst()
        .orElse(null);
  }


}
