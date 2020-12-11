import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * @author duwei7
 * @time 2020/12/10 18:21
 */
public class CallableTest  implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        System.out.println("state " + Thread.currentThread().getState() + " name " + Thread.currentThread().getName());
        return "hha";
    }

    public static void main(String[] args) {
        Callable<String> callable = new CallableTest();
        Callable<String> callable1 = new CallableTest();
        Callable<String> callable2 = new CallableTest();
        try {
            CompletableFuture.completedFuture(callable.call());
            callable1.call();
            callable2.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
