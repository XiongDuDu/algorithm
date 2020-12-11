package block;

import java.io.IOException;

/**
 * @author duwei7
 * @time 2020/12/2 10:37
 */
public class Main {
    public static void main(String[] args) {
        int num = 0;
        long start = System.currentTimeMillis() / 1000;
        while (true) {
            num = num + 1;
            if (num == Integer.MAX_VALUE) {
                System.out.println("reset");
                num = 0;
            }
            if ((System.currentTimeMillis() / 1000) - start > 1000) {
                return;
            }
        }
    }
}
