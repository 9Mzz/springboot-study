package memory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryFinder {

    public Memory get() {
        long free = Runtime.getRuntime()
                .freeMemory();
        long max = Runtime.getRuntime()
                .maxMemory();
        long total = Runtime.getRuntime()
                .totalMemory();
        long used = total - free;
        return new Memory(used, max);
    }

}
