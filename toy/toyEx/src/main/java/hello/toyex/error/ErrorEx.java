package hello.toyex.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/error")
@RestController
public class ErrorEx {

    @GetMapping("/{errorCode}")
    public String errorEx(@PathVariable("errorCode") String code) {

        log.info("error code = {}", code);

        if(code.equals("ill")) {
            throw new IllegalStateException("IllegalStateException 오류");
        }

        return "ok";
    }

}
