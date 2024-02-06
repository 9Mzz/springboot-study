package hello.toyex.members;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Members {

    private Long   id;
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    public Members() {
    }

    public Members(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
