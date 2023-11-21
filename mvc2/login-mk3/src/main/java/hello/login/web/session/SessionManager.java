package hello.login.web.session;


import java.util.Arrays;
import java.util.Map;
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
    String sessionId = UUID.randomUUID()
        .toString();
    sessionStore.put(sessionId, value);
    Cookie mySessionCookie = new Cookie(MY_SESSION_ID, sessionId);
    response.addCookie(mySessionCookie);
  }


  public Object getCookie(HttpServletRequest request) {
    Cookie mySessionCookie = getSession(request, MY_SESSION_ID);
    if (mySessionCookie == null) {
      return null;
    }
    return sessionStore.get(mySessionCookie.getValue());
  }

  public void expire(HttpServletRequest request) {
    Cookie mySessionCookie = getSession(request, MY_SESSION_ID);
    if (mySessionCookie != null) {
      sessionStore.remove(mySessionCookie.getValue());
    }
  }


  public Cookie getSession(HttpServletRequest request, String cookieName) {
    Cookie[] requestCookies = request.getCookies();
    if (requestCookies == null) {
      return null;
    }

    return Arrays.stream(requestCookies)
        .filter(cookie -> cookie.getName()
            .equals(cookieName))
        .findFirst()
        .orElse(null);

  }


}
