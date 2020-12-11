package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author duwei7
 * @time 2020/9/9 11:23
 */
public class TestCompletableFutureSet {
    private final static int AVILIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVILIABLE_PROCESSORS,
            AVILIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        POOL_EXECUTOR.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
            System.out.println("---" + Thread.currentThread().getName() + " set future result ----");
            future.complete("hello, dw");
        });

        System.out.println("---main thread wait future result---");
        System.out.println(future.get());
        System.out.println("---main thread got future result---");

        System.out.println("------runAsync");
        runAsync();
        System.out.println("------supplyAsync");
        supplyAsync();
        System.out.println("------thenRun");
        thenRun();
        System.out.println("------thenAccept");
        thenAccept();

        System.out.println("-----thenApply");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "hello, thenRun, dw";
        });

        CompletableFuture<String> future2 = future1.thenApply(s -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return s + " thenApply.";
        });
//        System.out.println(future2.get());

        System.out.println("------whenComplete");
        future1.whenComplete((s, throwable) -> {
            if(null == throwable) {
                System.out.println("走到了。。。");
                System.out.println(s);
            } else {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
        Thread.currentThread().join();
        POOL_EXECUTOR.shutdown();
    }

    public static void runAsync() throws Exception {
        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("over");
            }
        });

        System.out.println(future.get());
    }

    public static void supplyAsync() throws Exception {
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "hello, supplyAyn, dw";
            }
        });
        System.out.println(future.get());
    }

    public static void thenRun() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return "hello, thenRun, dw";
            }
        });

        CompletableFuture future2 = future1.thenRun(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
                System.out.println("---after future1 over doSomething.");
            }
        });

        System.out.println(future2.get());
    }

    public static void thenAccept() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return "hello, thenRun, dw";
            }
        });

        CompletableFuture future2 = future1.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("---after future1 over doSomething." + s);
            }
        });

        System.out.println(future2.get());
    }
}

