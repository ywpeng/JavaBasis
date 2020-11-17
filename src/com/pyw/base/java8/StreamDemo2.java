package com.pyw.base.java8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengyangwei
 * @create 2020-11-17 7:59
 */
public class StreamDemo2 {
    public static void main(String[] args) {


        /*   工作中常用的的 14 个Stream操作的示例
        java.util.Stream 表示能应用在一组元素上一次执行的操作序列。
        1.Stream 操作分为中间操作或者最终操作两种，最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样就可以将多个操作依次串起来。
        2.Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set， Map不支持。
        3.Stream的操作可以串行stream()执行或者并行parallelStream()执行。
         */
    }

    /**
     * 1.forEach 循环
     */
    public void forEach(){
        // 你不鸟我,我也不鸟你
        List<String> list = Arrays.asList("you", "don't", "bird", "me", ",",
                "I", "don't", "bird", "you");

        // 方式一：JDK1.8之前的循环方式
        for (String item: list) {
            System.out.println(item);
        }

        // 方式二：使用Stream的forEach方法
        // void forEach(Consumer<? super T> action)
        list.stream().forEach(item -> System.out.println(item));

        // 方式三：方式二的简化方式
        // 由于方法引用也属于函数式接口，所以方式二Lambda表达式也可以使用方法引用来代替
        // 此种方式就是方式一、方式二的简写形式
        list.stream().forEach(System.out::println);
    }

    /**
     * 2.filter过滤
     */
    public void filter(){
        List<User> users = Arrays.asList(
                new User(1L,"12", "mengday", 28),
                new User(2L,"12", "guoguo", 18),
                new User(3L, "12","liangliang", 17)
        );

        // Stream<T> filter(Predicate<? super T> predicate);
        users.stream().filter(user -> user.getAge() > 18).forEach(System.out::println);
    }



    /**
     * 3.map映射
     */
    public void map(){
        List<String> list = Arrays.asList("how", "are", "you", "how", "old", "are", "you", "?");
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
        list.stream().map(item -> item.toUpperCase()).forEach(System.out::println);
    }

    /**
     * 4.flatMap
     */
    public void flatMap(){
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5, 6);

        // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        List<List<Integer>> collect = Stream.of(a, b).collect(Collectors.toList());
        // [[1, 2, 3], [4, 5, 6]]
        System.out.println(collect);

        // 将多个集合中的元素合并成一个集合
        List<Integer> mergeList = Stream.of(a, b).flatMap(list -> list.stream()).collect(Collectors.toList());
        // [1, 2, 3, 4, 5, 6]
        System.out.println(mergeList);

        // 通过Builder模式来构建
        Stream<Object> stream = Stream.builder().add("hello").add("hi").add("byebye").build();
    }

    /**
     * 5.sorted排序
     */
    public void sort(){
        List<String> list = Arrays.asList("c", "e", "a", "d", "b");
        // Stream<T> sorted(Comparator<? super T> comparator);
        // int compare(T o1, T o2);
        list.stream().sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::println);
    }

    /**
     * 6.distinct去重
     */
    public void distinct(){
        // 知之为知之,不知为不知
        Stream<String> stream = Stream.of("know", "is", "know", "noknow", "is", "noknow");
        stream.distinct().forEach(System.out::println); // know is noknow
    }

    /**
     * 7.count总数量
     */
    public void count(){
        Stream<String> stream = Stream.of("know", "is", "know", "noknow", "is", "noknow");
        long count = stream.count();
        System.out.println(count);
    }

    /**
     * 8.min、max
     */
    public void min(){
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        // Optional<T> min(Comparator<? super T> comparator);
        Optional<String> optional = list.stream().min((a, b) -> a.compareTo(b));
        String value = optional.get();
        System.out.println(value);
    }

    /**
     * 9.skip、limit
     */
    public void skip(){
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        // Stream<T> skip(long n)
        list.stream().skip(2).forEach(System.out::println);  // c、d、e
    }

    public void limit(){
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        list.stream().skip(2).limit(2).forEach(System.out::println);    // c、d
    }

    /**
     *10.collect
     */
    public void collect(){
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        // Stream -> Collection
        List<String> collect = list.stream().collect(Collectors.toList());

        // Stream -> Object[]
        Object[] objects = list.stream().toArray();
    }

    /**
     * 11.concat
     */
    public void concat(){
        List<String> list = Arrays.asList("a", "b");
        List<String> list2 = Arrays.asList("c", "d");
        Stream<String> concatStream = Stream.concat(list.stream(), list2.stream());
        concatStream.forEach(System.out::println);
    }

    /**
     * 12.anyMatch、allMatch
     */
    public void match(){
        // 你给我站住
        List<String> list = Arrays.asList("you", "give", "me", "stop");
        // boolean anyMatch(Predicate<? super T> predicate);
        // parallelStream可以并行计算，速度比stream更快
        boolean result = list.parallelStream().anyMatch(item -> item.equals("me"));
        System.out.println(result);
    }

    /**
     * anyMatch伪代码
     * 如果集合中有一个元素满足条件就返回true
     * @return
     */
    public  boolean anyMatch() {
        List<String> list = Arrays.asList("you", "give", "me", "stop");
        for (String item : list) {
            if (item.equals("me")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 13.reduce归纳
     */
    public void reduce(){
        Stream<String> stream = Stream.of("you", "give", "me", "stop");
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        Optional<String> optional = stream.reduce((before, after) -> before + "," + after);
        optional.ifPresent(System.out::println);    // you,give,me,stop
    }

    public  void sum() {
        List<BigDecimal> list = Arrays.asList(
                new BigDecimal("11.11"),
                new BigDecimal("22.22"),
                new BigDecimal("33.33")
        );
        // 66.66
        BigDecimal sum = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }

    /**
     * 14.findFirst、findAny
     */
    public void findFirst(){
        Stream<String> stream = Stream.of("You", "give", "me", "stop");
        String value = stream.findFirst().get();

        System.out.println(value);
    }

    public void findAny(){
        Stream<String> stream = Stream.of("you", "give", "me", "stop");
        String value2 = stream.findAny().get();
        System.out.println(value2);
    }

    public class User {
        private Long id;
        private String phone;
        private String username;
        private Integer age;

        public User(){}
        public User(Long id,String phone, String username, Integer age) {
            this.id = id;
            this.phone = phone;
            this.username = username;
            this.age = age;
        }
        // Getter & Setter & toString
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}


