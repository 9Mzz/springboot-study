package test.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.core.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller @RequiredArgsConstructor public class LogDemoController {

    private final LogDemoService           logDemoService;
    private final ObjectProvider<MyLogger> myLoggersProvider;
    //    private final  MyLogger       myLogger;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {

        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger   = myLoggersProvider.getObject();

        //URL 설정
        myLogger.setRequestURL(requestURL);

        myLogger.log(" controller test");

        logDemoService.logic("testId");

        return "OK";

    }

}
