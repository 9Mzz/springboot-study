package hello.login.web.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {

  private String error;
  private String errorMessage;
}
