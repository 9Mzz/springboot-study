package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.invoke.StringConcatException;

@Getter
@Setter
@ToString
public class Member2 {

    private Long   id;
    private String username;
    private int    age;

    //
    public Member2() {
    }

    public Member2(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
