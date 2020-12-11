package completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author duwei7
 * @time 2020/9/9 15:35
 */
public class StreamTestFuture {
    public static String rpcCall(String ip, String param) {
        System.out.println(ip + " rpcCall:" + param);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return param;
    }

    public static void main(String[] args) {
        List<String> ipList = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            ipList.add("192.168.0." + i);
        }

        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> futureList = ipList.stream()
                .map(ip -> CompletableFuture.supplyAsync(() -> rpcCall(ip, ip))).collect(Collectors.toList());
        List<String> resultList = futureList.stream()
                .map(future -> future.join())
                .collect(Collectors.toList());

        resultList.stream().forEach(r -> System.out.println(r));
        System.out.println("parallel cost: " + (System.currentTimeMillis() - start));

        long startSerial = System.currentTimeMillis();
        List<String> resultSerial = new ArrayList<>();
        for(String ip : ipList) {
            resultSerial.add(rpcCall(ip, ip));
        }
        resultSerial.stream().forEach(x -> System.out.println(x));
        System.out.println("serial cost: " + (System.currentTimeMillis() - startSerial));


    }
}
