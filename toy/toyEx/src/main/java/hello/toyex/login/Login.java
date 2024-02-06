package hello.toyex.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {

    private String loginId;
    private String password;

    public Login() {
    }
}
