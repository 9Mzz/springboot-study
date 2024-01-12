package hello.totalproject.converter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
