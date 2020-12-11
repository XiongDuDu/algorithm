import java.util.concurrent.*;

/**
 * @author duwei7
 * @time 2020/11/30 14:09
 */
public class Worker implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    public static void main(String[] args) {
        //1 AbortPolicy  将抛出 RejectedExecutionException.
//		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

//		2 DiscardPolicy 用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。运行结果也不会全部执行100个线程。
        RejectedExecutionHandler handler =  new ThreadPoolExecutor.DiscardPolicy();

//		3 DiscardOldestPolicy
        //在pool没有关闭的前提下首先丢掉缓存在队列中的最早的任务（工作队列头部），然后重新尝试运行该任务
        //这样运行结果就不会有100个线程全部被执行
//		RejectedExecutionHandler handler =  new ThreadPoolExecutor.DiscardOldestPolicy();

//		4 CallerRunsPolicy 线程调用运行该任务的 execute 本身
//		RejectedExecutionHandler handler =  new ThreadPoolExecutor.CallerRunsPolicy();
        subTask(handler);
    }
    public static void	subTask(RejectedExecutionHandler handler){
        int corePoolSize = 2;
        int maxPoolSize = 4;
        System.out.println(corePoolSize + "   "+maxPoolSize);
        long keepAliveTime = 5;
//        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new SynchronousQueue<>(), handler);
        for (int i = 0; i < 100; i++) {
            executor.execute(new Worker());
        }
        executor.shutdown();
    }
}
