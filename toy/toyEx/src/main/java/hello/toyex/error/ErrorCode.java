package hello.toyex.error;

import lombok.Data;

@Data
public class ErrorCode {

    private String code;
    private String message;

    public ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
