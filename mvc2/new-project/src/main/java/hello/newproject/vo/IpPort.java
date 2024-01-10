package hello.newproject.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class IpPort {

  private String  ip;
  private Integer port;

  public IpPort(String ip, Integer port) {
    this.ip = ip;
    this.port = port;
  }
}
