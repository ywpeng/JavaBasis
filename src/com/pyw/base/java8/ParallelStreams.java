package com.pyw.base.java8;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author pengyangwei
 * @create 2020-11-26 11:26
 */
public class ParallelStreams {
    public static void main(String[] args) {
        /*
        流可以是顺序的，也可以是并行的。顺序流上的操作在单个线程上执行，而并行流上的操作在多个线程上并发执行。

        下面的示例演示了使用并行流来提高性能是多么的容易。亲测提升了1倍性能！
         */
        //首先，我们创建一个较大的List：
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        /*
        1. Sequential Sort 顺序流排序
         */
        // 纳秒
//        long t0 = System.nanoTime();
//        long count = values.stream().sorted().count();
//        System.out.println(count);
//        long t1 = System.nanoTime();
//        // 纳秒转微秒
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("顺序流排序耗时: %d ms", millis));
//        //顺序流排序耗时: 712 ms

        /*
        2. Parallel Sort 并行流排序
         */
//        long t0 = System.nanoTime();
//        long count = values.parallelStream().sorted().count();
//        System.out.println(count);
//        long t1 = System.nanoTime();
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("parallel sort took: %d ms", millis));
//        //parallel sort took: 385 ms

        /*
        3.对Map的流操作
        Map是不支持 Stream 流的，因为 Map 接口并没有像 Collection 接口那样，定义了 stream() 方法。但是，我们可以对其 key, values, entry 使用 流操作，
        如 map.keySet().stream(), map.values().stream() 和 map.entrySet().stream().
         */
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前， 不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, "val" + i);
        }
        // forEach 可以很方便地对 map 进行遍历操作
        map.forEach((key, value) -> System.out.println(value));

        //对 Map的其他操作可参考：
//        test27();

        // merge 方法，会先判断进行合并的 key 是否存在，不存在，则会添加元素
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9); // val9
        // 若 key 的元素存在，则对 value 执行拼接操作
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9); // val9concat
    }

    }


//    public void test27() {
//        // 如下：对 key 为 3 的值，内部会先判断值是否存在，存在，则做 value + key 的拼接操作
//        map.computeIfPresent(3, (num, val) -> val + num);
//        map.get(3); // val33
//
//        // 先判断 key 为 9 的元素是否存在，存在，则做删除操作
//        map.computeIfPresent(9, (num, val) -> null);
//        map.containsKey(9); // false
//
//        // computeIfAbsent(), 当 key 不存在时，才会做相关处理
//        // 如下：先判断 key 为 23 的元素是否存在，不存在，则添加
//        map.computeIfAbsent(23, num -> "val" + num);
//        map.containsKey(23); // true
//
//        // 先判断 key 为 3 的元素是否存在，存在，则不做任何处理
//        map.computeIfAbsent(3, num -> "bam");
//        map.get(3); // val33
//    }


}


