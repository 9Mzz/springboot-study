package hello.toy.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {

        //"127.0.0.1:8080"
        String[] split = source.split(":");
        String   Ip    = split[0];
        Integer  Port  = Integer.valueOf(split[1]);

        return new IpPort(Ip, Port);
    }
}
