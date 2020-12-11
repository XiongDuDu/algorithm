package forkjoinsum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 分治算法的多线程编写方式
 *
 * @author duwei7
 * @time 2020/12/9 15:24
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) {
            return computeSeq();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        long right = rightTask.compute();
        long left = leftTask.join();
        return left + right;
    }

    private long computeSeq() {
        long sum = 0;
        for (int i = start; i < end; ++i) {
            sum += i;
        }
        return sum;
    }
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }

    public static void main(String[] args) {
        System.out.println(forkJoinSum(100_000));
    }
}
