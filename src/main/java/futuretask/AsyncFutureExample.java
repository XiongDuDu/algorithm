package futuretask;

import java.util.concurrent.FutureTask;

/**
 * @author duwei7
 * @time 2020/9/9 15:57
 */
public class AsyncFutureExample {
    public static String doSomethingA() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--- doSomethingA ---");
        return "a result";
    }
    public static String doSomethingB() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--- doSomethingB ---");
        return "b result";
    }

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();

        FutureTask<String> futureTask = new FutureTask<String>( () -> {
            String result = null;
            try {
                result = doSomethingA();
            }catch (Exception E) {

            }

            return  result;
        });

        Thread thread = new Thread(futureTask, "threadA");
        thread.start();

        String taskBResult = doSomethingB();
        String taskAResult = futureTask.get();

        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);
    }

}
