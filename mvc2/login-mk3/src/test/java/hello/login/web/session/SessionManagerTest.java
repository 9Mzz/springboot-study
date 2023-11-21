package hello.login.web.session;

import static org.junit.jupiter.api.Assertions.*;

import hello.login.web.member.Member;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

  SessionManager sessionManager = new SessionManager();

  @Test
  void createSession() {

    //세션 생성
    Member                  member   = new Member();
    MockHttpServletResponse response = new MockHttpServletResponse();
    sessionManager.createSession(member, response);

    //쿠키 저장
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setCookies(response.getCookies());

    //쿠키 조회
    Object result = sessionManager.getCookie(request);
    org.assertj.core.api.Assertions.assertThat(result)
        .isEqualTo(member);

    //쿠키 제거
    sessionManager.expire(request);

    Object expired = sessionManager.getCookie(request);
    org.assertj.core.api.Assertions.assertThat(expired)
        .isNull();

  }
}