package completablefuture;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author duwei7
 * @time 2020/9/9 17:52
 */
public class test {
    public static void main(String[] args) {
        AlpLocalUnit alpLocalUnit = new AlpLocalUnit();
        AlpLocalUnit alpLocalUnit1 = new AlpLocalUnit();
        AlpLocalUnit alpLocalUnit2 = new AlpLocalUnit();
        AlpLocalUnit alpLocalUnit3 = new AlpLocalUnit();
        AlpLocalUnit alpLocalUnit4 = new AlpLocalUnit();
        alpLocalUnit.setUnitId(1);
        alpLocalUnit1.setUnitId(2);
        alpLocalUnit2.setUnitId(2);
        alpLocalUnit3.setUnitId(4);
        alpLocalUnit4.setUnitId(5);
        List<AlpLocalUnit> res = new ArrayList<>();
        res.add(alpLocalUnit);
        res.add(alpLocalUnit1);
        res.add(alpLocalUnit2);
        res.add(alpLocalUnit3);
        res.add(alpLocalUnit4);

        res.stream().map(AlpLocalUnit::getUnitId).collect(Collectors.toSet());

        Set<AlpLocalUnit> alpLocalUnitSet = new TreeSet<AlpLocalUnit>(new Comparator<AlpLocalUnit>() {
            @Override
            public int compare(AlpLocalUnit a, AlpLocalUnit b) {

                return (int) (a.getUnitId() - b.getUnitId());
            }
        });
        alpLocalUnitSet.addAll(res);

        Map<Long, AlpLocalUnit> resMap = alpLocalUnitSet.stream().collect(Collectors.toMap(AlpLocalUnit::getUnitId, x -> x));
        System.out.println(resMap.size());

        ConcurrentHashMap<String, ConcurrentHashMap<Long, Integer>> co = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> co1 = new ConcurrentHashMap<>();
        co.computeIfAbsent("redis", k -> new ConcurrentHashMap<>()).put(1L, 1);
        System.out.println(co1.toString());
        System.out.println(co1.compute("12", (key, value) -> {
            if(value == null) {
                return "Nah";
            } else {
                return "you";
            }
        }));
        System.out.println(co1.toString());

    }
}
