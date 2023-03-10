package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "httpServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //Servlet이 호출되면 Service가 실행
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //쿼리파라미터로 조회
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //text/plain text/mxl 등 다양히 있음
        response.setContentType("text/plain");
        //인코딩 설정
        response.setCharacterEncoding("utf-8");
        //HTTP 메세지 바디에 데이터가 들어감
        response.getWriter().write("hello " + username);


    }

}

