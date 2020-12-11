package completablefuture;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author duwei7
 * @time 2020/12/3 16:48
 */
public class ComposeUsage {
    private final static int AVILIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(1,
            1, 0, TimeUnit.MINUTES, new SynchronousQueue<>(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> futureCopy = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("futureCopy  " + Thread.currentThread().getName());
            } catch (Exception E) {

            }
            return "futureCopy ";
        }, POOL_EXECUTOR);

        AtomicLong start = new AtomicLong(0);
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("future1  " + Thread.currentThread().getName());
            } catch (Exception E) {

            }
            return "future1 ";
        });

        future1 = future1.thenCompose(str1 -> {
            Long t1= System.currentTimeMillis();
            start.set(System.currentTimeMillis());
            CompletableFuture<String> res = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(10000);
                    System.out.println("future1 thenCompose " + Thread.currentThread().getName());
                } catch (Exception E) {

                }
                return "thenCompose";
            });
            System.out.println("real time : " + (System.currentTimeMillis() - t1));
            return res;
        });
        future1 = future1.whenComplete((data, e) -> {
            System.out.println(System.currentTimeMillis() - start.get());
        });

//        futureCopy.thenCombine(future1, (copy, f1) -> {
//            System.out.println("combine " + Thread.currentThread().getName());
//            return null;
//        });
//
//        future1 = future1.thenComposeAsync(x -> {
//            System.out.println("enter f2");
////            throw new NullPointerException();
//            CompletableFuture<String> tmp = getFuture2(x);
//            System.out.println("buhuashijian dee    ");
//            return tmp;
//        }, POOL_EXECUTOR);


//        CompletableFuture<String> future2 = future1.thenApply(x -> {
//            try {
//                Thread.sleep(1000);
//                System.out.println("thenApply  " + Thread.currentThread().getName());
//            } catch (Exception E) {
//
//            }
//            return x + "-thenApply ";
//        });

//        future1 = future1.whenCompleteAsync((s, throwable) -> {
//            System.out.println("whenComplete  " + Thread.currentThread().getName());
//            if (throwable != null) {
//                System.out.println(throwable.getMessage());
//            }
//            System.out.println("ending.");
//        }, POOL_EXECUTOR);
//        future1.exceptionally((a) -> {
//            System.out.println("final thread " + Thread.currentThread().getName());
//            return null;
//        });

        try {
//            futureCopy.get();
            future1.get();
            POOL_EXECUTOR.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static CompletableFuture<String> getFuture2(String str) {
        System.out.println("thenCompose " + Thread.currentThread().getName());
        CompletableFuture<String> tmp = CompletableFuture.supplyAsync(() -> {
            System.out.println("getFuture2   " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (Exception E) {

            }
            return str + "-getFuture2";
        });
        tmp.exceptionally(x -> {
            System.out.println("error" + x.toString());
            return "error";
        });
        return tmp;
    }
}
