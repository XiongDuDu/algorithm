package stream;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author duwei7
 * @time 2020/12/8 16:33
 */
public class StreamTest {
    static class Apple {
        public Apple(String name, Integer weight) {
            this.name = name;
            this.weight = weight;
        }
        String name;
        Integer weight;
    }
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("a1", 10));
        apples.add(new Apple("a2", 9));
        apples.add(new Apple("a3", 8));
        apples.parallelStream().filter(x -> {
            System.out.println("filter " + x.name + Thread.currentThread().getName());
            return x.weight > 8;
        }).map(x -> {
            System.out.println("map " + x.name + Thread.currentThread().getName());
            return x.weight;
        }).limit(2);

        int n=1;
        IntStream.range(2,n).boxed().collect(Collectors.toSet());

        List<Integer> list = Arrays.asList(1,2,3,4,5);
         Stream<Integer> stream = list.stream();
        List<Integer> numbers = stream.reduce(new ArrayList<Integer>(), (List<Integer> l, Integer e) -> {
            l.remove(e);
            return l;
        }, (List<Integer> l1, List<Integer> l2) -> {
            l1.addAll(l2);
            return l1;
        });
        System.out.println(numbers.get(0));
    }
}
