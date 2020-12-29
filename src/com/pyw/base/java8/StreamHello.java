package com.pyw.base.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author pengyangwei
 * @create 2020-11-26 11:13
 */
public class StreamHello {
    public static void main(String[] args) {
        /*
         java.util.Stream 对一个包含一个或多个元素的集合做各种操作。这些操作可能是 中间操作 亦或是 终端操作。 终端操作会返回一个结果，而中间操作会返回一个 Stream 流。
         只能对实现了 java.util.Collection 接口的类做流的操作。
         Stream 流支持同步执行，也支持并发执行。
         注意：Map不支持Stream流，但是他的key和value是支持的！

         */
        //  以字符串列表的形式创建一个示例
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        /*
        1. Filter 过滤
        Filter 的入参是一个 Predicate, 上面已经说到，Predicate 是一个断言的中间操作，它能够帮我们筛选出我们需要的集合元素。
        它的返参同样 是一个 Stream 流，我们可以通过 foreach 终端操作，来打印被筛选的元素：
         */
//        stringCollection
//                .stream()
//                .filter((s) -> s.startsWith("a"))
//                .forEach(System.out::println);

        /*
        2.Sorted 排序
        Sorted 同样是一个中间操作，它的返参是一个 Stream 流。另外，我们可以传入一个 Comparator 用来自定义排序，
        如果不传，则使用默认的排序规则。
         */
//        stringCollection
//                .stream()
//                .sorted()
//                .filter((s) -> s.startsWith("a"))
//                .forEach(System.out::println);

        /*
        3. Map 转换
        中间操作映射通过给定的函数将每个元素转换为另一个对象。
        例如下面的示例，通过 map 我们将每一个 string 转成大写：
         */
//        stringCollection
//                .stream()
//                .map(String::toUpperCase)
//                .sorted(Comparator.reverseOrder()) //等同于(a, b) -> b.compareTo(a)
//                .forEach(System.out::println);

        /*
        4.Match 匹配
        match 用来做匹配操作，它的返回值是一个 boolean 类型。通过 match,
        我们可以方便的验证一个 list 中是否存在某个类型的元素。
         */
        // anyMatch：验证 list 中 string 是否有以 a 开头的, 匹配到第一个，即返回 true
//        boolean anyStartsWithA =
//                stringCollection
//                        .stream()
//                        .anyMatch((s) -> s.startsWith("a"));
//        System.out.println(anyStartsWithA); // true
//        // allMatch：验证 list 中 string 是否都是以 a 开头的
//        boolean allStartsWithA =
//                stringCollection
//                        .stream()
//                        .allMatch((s) -> s.startsWith("a"));
//        System.out.println(allStartsWithA); // false
//        // noneMatch：验证 list 中 string 是否都不是以 z 开头的
//        boolean noneStartsWithZ =
//                stringCollection
//                        .stream()
//                        .noneMatch((s) -> s.startsWith("z"));
//        System.out.println(noneStartsWithZ); // true


        /*
        5.Count 计数
        count 是一个终端操作，它能够统计 stream 流中的元素总数，返回值是 long 类型。
         */
        // count：先对 list 中字符串开头为 b 进行过滤，让后统计数量
//        long startsWithB =
//                stringCollection
//                        .stream()
//                        .filter((s) -> s.startsWith("b"))
//                        .count();
//        System.out.println(startsWithB); // 3

        /*
        6. Reduce
        Reduce 中文翻译为：减少、缩小。通过入参的 Function，我们能够将 list 归约成一个值。
        它的返回类型是 Optional 类型。
         */
//        Optional<String> reduced =
//                stringCollection
//                        .stream()
//                        .sorted()
//                        .reduce((s1, s2) -> s1 + "#" + s2);
//        reduced.ifPresent(System.out::println);
//        // aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2


    }
}
