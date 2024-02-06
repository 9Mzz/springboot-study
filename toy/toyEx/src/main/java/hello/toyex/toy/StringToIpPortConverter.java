package hello.toyex.toy;

import org.springframework.core.convert.converter.Converter;

public class StringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) {
        String[] split = source.split(":");
        String   ip    = split[0];
        Integer  Port  = Integer.valueOf(split[1]);

        return new IpPort(ip, Port);
    }
}
