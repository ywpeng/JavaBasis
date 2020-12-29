package com.pyw.base.functional;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author pengyangwei
 * @create 2020-11-26 9:19
 */
public class InnerFunctionalInterface {

    public static void main(String[] args) {
        /*
        JDK 1.8 API 包含了很多内置的函数式接口,Java 8 为他们都添加了 @FunctionalInterface 注解，以用来支持 Lambda 表达式。
        主要分为 五大类：消费性接口、供给型接口、函数型接口、断定型接口、比较型接口
         */

        /*
//		 * Java 内置核心四大函数式接口
//		 * 接口名称		参数类型	返回类型	用途
//		 * ---------------------------------------
//		 * Consumer<T>	T		void	对类型为T的对象应用操作，包含方法 :
//		 * 	消费型接口						void accept(T t)
//		 * Supplier<T>	无		T		返回类型为T的对象，包含方法：
//		 * 	供给型接口						T get();
//		 * Function<T,R>T		R		对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：
//		 * 函数型接口						R apply(T t)
//		 * Predicate<T>	T		boolean	确定类型为T的对象是否满足某约束，并返回Boolean值。包含反复：
//		 * 断定型接口						boolean test(T t)
//		 */
        InnerFunctionalInterface ifi = new InnerFunctionalInterface();

        // 接口一：断定型接口
        ifi.testPredicate();

        // 接口二：函数型接口
        ifi.testFunction();

        // 接口三：供给型接口
        ifi.testSupplier();

        // 接口四：消费型接口
        ifi.testConsumer();

    }

    /**
     * 1.断言接口
     *Predicate 是一个可以指定入参类型，并返回 boolean 值的函数式接口。它内部提供了一些带有默认实现的方法，可以 被用来组合一个复杂的逻辑判断（and, or, negate）：
     */
    public void testPredicate() {
        Predicate<String> predicate = (s) -> s.length() > 0;

        boolean foo0 = predicate.test("foo"); // true
        boolean foo1 = predicate.negate().test("foo"); // negate否定相当于!true

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    /**
     * 2.函数型接口
     *Function 函数式接口的作用是，我们可以为其提供一个原料，他给生产一个最终的产品。
     * 通过它提供的默认方法，组合,链行处理(compose, andThen)：
     */
    public void testFunction() {
//        Function<String, Integer> toInteger = Integer::valueOf; //转Integer
//        Function<String, String> backToString = toInteger.andThen(String::valueOf); //转String
//        Function<String, String> afterToStartsWith = backToString.andThen(new Something()::startsWith); //截取第一位
//        String apply = afterToStartsWith.apply("123");// "123"
//        System.out.println(apply);
    }

    /**
     * 3.供给型接口
     *Supplier 与 Function 不同，它不接受入参，直接为我们生产一个指定的结果，有点像生产者模式：
     */
    public void testSupplier() {
//        Supplier<Person> personSupplier0 = Person::new;
//        personSupplier0.get(); // new Person
//        Supplier<String> personSupplier1 = Something::test01; //这个test方法是静态的，且无入参
//        personSupplier1.get(); // hi
//
//        Supplier<String> personSupplier2 = new Something()::test02;
    }

    /**
     * 4.消费型接口
     *对于 Consumer，我们需要提供入参，用来被消费，如下面这段示例代码：
     */
    public void testConsumer() {
        // 参照物，方便知道下面的Lamdba表达式写法
//        Consumer<Person> greeter01 = new Consumer<Person>() {
//            @Override
//            public void accept(Person p) {
//                System.out.println("Hello, " + p.firstName);
//            }
//        };
//        Consumer<Person> greeter02 = (p) -> System.out.println("Hello, " + p.firstName);
//        greeter02.accept(new Person("Luke", "Skywalker")); //Hello, Luke
//        Consumer<Person> greeter03 = new MyConsumer<Person>()::accept; // 也可以通过定义类和方法的方式去调用，这样才是实际开发的姿势
//        greeter03.accept(new Person("Luke", "Skywalker")); //Hello, Luke
    }

    /**
     * 5.比较型接口
     *对于 Consumer，我们需要提供入参，用来被消费，如下面这段示例代码：
     */
    public void testComparator(){
//        Comparator<Person> comparator01 = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
//        Comparator<Person> comparator02 = Comparator.comparing(p -> p.firstName); //等同于上面的方式
//        Person p1 = new Person("John", "Doe");
//        Person p2 = new Person("Alice", "Wonderland");
//        comparator01.compare(p1, p2); // > 0
//        comparator02.reversed().compare(p1, p2); // < 0
    }

}
