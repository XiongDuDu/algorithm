package waitnotify;

/**
 * @author duwei7
 * @time 2020/12/11 16:17
 */
public class ThreadA implements Runnable {
    Service lock;

    public ThreadA(Service lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                int n = lock.n;
                int i = 0;
                while (i < n) {
                    lock.wait();
                    if (i % 2 == 0) {
                        System.out.println(i + " name is t1");
                    }
                    ++i;
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
