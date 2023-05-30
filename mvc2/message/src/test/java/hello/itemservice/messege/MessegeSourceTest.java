package hello.itemservice.messege;

import org.assertj.core.api.Assertions;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessegeSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void hellloMessage() {
        String result = messageSource.getMessage("hello", null, null);

        messageSource.getMessage("안녕", null, Locale.KOREA);

        //        System.out.println(Locale.getDefault());

        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFountMessageCode() {
        //        messageSource.getMessage("no_code", null, null);

        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null)).isInstanceOf(
                NoSuchMessageException.class);

    }

    @Test
    void noFoundMessageCode() {
        String result = messageSource.getMessage("no_code", null, "기본 메시지", null);

        System.out.println("result = " + result);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        String result = messageSource.getMessage("hello.name", new Object[]{"spring"}, null);
        assertThat(result).isEqualTo("안녕 spring");

    }

    @Test
    void defaultLang() {
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
        String hello = messageSource.getMessage("hello", null, Locale.ENGLISH);
        System.out.println("hello = " + hello);
    }

    @Test
    void enLang() {
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }


}
