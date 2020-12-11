package block;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author duwei7
 * @time 2020/9/9 11:23
 */
public class BlockExecutor {
    private final static int AVILIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(1,
            1, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(1),
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        Future<String> future = POOL_EXECUTOR.submit(new Task());
        /*try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }

    public static class Task implements Callable<String>{
        @Override
        public String call() {
            while (true) {

            }
        }
    }
}

