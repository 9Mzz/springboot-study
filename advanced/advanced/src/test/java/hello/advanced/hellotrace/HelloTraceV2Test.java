package hello.advanced.hellotrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus  status1 = traceV2.begin("hello 1");
        TraceStatus  status2 = traceV2.beginSync(status1.getTraceId(), "hello 2");
        traceV2.end(status2);
        traceV2.end(status1);


    }

    @Test
    void begin_exception() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus  status1 = traceV2.begin("hello 1");
        TraceStatus  status2 = traceV2.beginSync(status1.getTraceId(), "hello 2");
        traceV2.exception(status2, new IllegalStateException());
        traceV2.exception(status2, new IllegalStateException());
    }
}