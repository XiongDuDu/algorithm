package waitnotify;

/**
 * @author duwei7
 * @time 2020/12/11 16:21
 */
public class ThreadB implements Runnable {
    Service lock;

    public ThreadB(Service lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                int n = lock.n;
                int i = 0;
                while (i <= n) {
                    lock.notify();
                    if (i % 2 == 1) {
                        System.out.println(i + " name is t2");
                    }
                    ++i;
                    lock.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
