package test.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.core.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    //ObjectPrivider 적용
    //    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    public void logic(String id) {
        //        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id = " + id);

    }
}
