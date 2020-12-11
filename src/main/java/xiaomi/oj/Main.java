package xiaomi.oj;

/**
 * @author duwei7
 * @time 2020/10/23 10:13
 */
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String args[]) {
       /* Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            String[] array = line.split(" ");
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                int a = Integer.parseInt(array[i]);
                sum = sum + a;
            }
            System.out.println(sum);
        }*/

        List<Long> linkedList = new LinkedList<>();
        linkedList.add(1L);
        linkedList.get(0);

        Map<Long, Long> hash = new HashMap<>();
        hash.put(1L, 1L);

        Map<Long, Long> linkedMap = new LinkedHashMap<>();
        linkedMap.put(1L, 9L);

        // create a ConcurrentHashMap and add some values
        ConcurrentHashMap<String, Integer>
                map = new ConcurrentHashMap<>();
        map.put("Book1", 10);
        map.put("Book2", 500);
        map.put("Book3", 400);

        // print map details
        System.out.println("ConcurrentHashMap: "
                + map.toString());

        // remap the values of ConcurrentHashMap
        // using compute method
        map.compute("Book2", (key, val)
                -> val + 100);
        map.compute("Book1", (key, val)
                -> val + 512);

        // print new mapping
        System.out.println("New ConcurrentHashMap: "
                + map);

    }
}
