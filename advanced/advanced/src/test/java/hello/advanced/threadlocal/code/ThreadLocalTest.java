package hello.advanced.threadlocal.code;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {


    static class MadThread extends Thread {
        private static final ThreadLocal<String> THREAD_LOCAL  = new ThreadLocal<>();
        private static final ThreadLocal<String> THREAD_LOCAL2 = ThreadLocal.withInitial(() -> "default_name");
        private final        String              name;


        public MadThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " Started, ThreadLocal = " + THREAD_LOCAL.get());
            THREAD_LOCAL.set(name);
            System.out.println(name + " Finished, ThreadLocal = " + THREAD_LOCAL.get());
            THREAD_LOCAL.remove();
        }
    }

    @Test
    void threadTest() throws InterruptedException {
        System.out.println("true = " + true);
        for (int threadCouunt = 1; threadCouunt <= 5; threadCouunt++) {
            final MadThread thread = new MadThread("thread-" + threadCouunt);
            thread.start();
        }


    }
}
