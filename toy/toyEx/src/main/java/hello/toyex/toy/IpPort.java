package hello.toyex.toy;

import lombok.Data;

@Data
public class IpPort {

    private String  ip;
    private Integer port;

    public IpPort(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }
}
