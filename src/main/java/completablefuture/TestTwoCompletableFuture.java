package completablefuture;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author duwei7
 * @time 2020/9/9 14:31
 */
public class TestTwoCompletableFuture {
    public static CompletableFuture<String> doSomethingOne(String str) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                System.out.println(str);
                return str;
            }
        });
    }

    public static CompletableFuture<String> doSomethingTwo(String str) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                }

                return "after" + str;
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<List<String>> stringSeq = CompletableFuture.supplyAsync(() -> {
            return new ArrayList<>();
        });
        stringSeq = stringSeq.thenCombine(CompletableFuture.supplyAsync(() -> {
            return Arrays.asList("2");
        }), (one, two) -> {
            List<String> combine = new ArrayList<>();
            combine.addAll(one);
            combine.addAll(two);
            return combine;
        });
        stringSeq = stringSeq.thenCombine(CompletableFuture.supplyAsync(() -> {
            return Arrays.asList("3");
        }), (one, two) -> {
            List<String> combine = new ArrayList<>();
            combine.addAll(one);
            combine.addAll(two);
            return combine;
        });
        System.out.println(" start");
        System.out.println(StringUtils.join(stringSeq.get(), ","));
        System.out.println(" start");


        CompletableFuture result1 = doSomethingOne("return").thenCompose(id -> doSomethingTwo(id));
        System.out.println("thenCompose get is " + result1.get());
        CompletableFuture result2 = doSomethingOne("123").thenCombine(doSomethingTwo("456"), (one, two) -> {
            return one + " " + two;
        });
        System.out.println(result2.get());
        System.out.println("---allOf");
        allOf();
        System.out.println("---anyOf");
        anyOf();

        System.out.println("---exception");
        CompletableFuture<String> future = new CompletableFuture<String>();
        new Thread(() -> {
            try {
                if (true) {
                    throw new RuntimeException("excetion test");
                }
                future.complete("ok");
            } catch (Exception e) {
                future.completeExceptionally(e);
            }

            System.out.println("----" + Thread.currentThread().getName() + " set future result----");


        }, "thread-1").start();

        System.out.println(future.get());

        System.out.println(future.exceptionally(t -> "default").get());
    }

    public static void allOf() throws InterruptedException, ExecutionException {
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(doSomethingOne("1"));
        futureList.add(doSomethingOne("2"));
        futureList.add(doSomethingOne("3"));
        futureList.add(doSomethingOne("4"));

        CompletableFuture<Void> result = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        System.out.println(result.get());
        CompletableFuture<Void> result1 = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        System.out.println(result1.get());
    }

    public static void anyOf() throws InterruptedException, ExecutionException {
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(doSomethingOne("1"));
        futureList.add(doSomethingOne("2"));

        CompletableFuture<Object> result = CompletableFuture.anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        System.out.println(result.get());

    }


}
