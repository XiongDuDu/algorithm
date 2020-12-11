package waitnotify;

/**
 * 两个线程交替打印n以内的奇偶数
 * @author duwei7
 * @time 2020/12/11 16:10
 */
public class PrintNumberAlternately {
    public static void main(String[] args) {
        Service lock=new Service();
        Thread a=new Thread(new ThreadA(lock));
        Thread b=new Thread(new ThreadB(lock));
        a.start();
        b.start();
    }
}
