package memory;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Memory {

    private long used;
    private long amx;

    public Memory(long used, long amx) {
        this.used = used;
        this.amx  = amx;
    }
}
