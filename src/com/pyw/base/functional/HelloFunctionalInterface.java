package com.pyw.base.functional;

/**
 * @author pengyangwei
 * @create 2020-11-26 8:43
 */
public class HelloFunctionalInterface {

    public static void main(String[] args) {
    /*
    通过一些例子我们可以看到Lambda可以开发出同样功能的逻辑但是代码却很简单，那么Jvm是如何进行类型推断，并且找到对应的方法呢？
    通过官文介绍以及我们使用发现，并不是每个接口都可以缩写成Lambda表达式的开发方式。其实是只有那些函数式接口(Functional Interface)才能缩写成 Lambda 表示式。

    所谓函数式接口(Functional Interface)就是只包含一个抽象方法的声明。针对该接口类型的所有 Lambda 表达式都会与这个抽象方法匹配。{另外，只是在接口上添加default并不算抽象方法}

    总结：为了保证一个接口明确的被定义为一个函数式接口(Functional Interface)，我们需要为该接口添加注解：@FunctionalInterface。这样，一旦你添加了第二个抽象方法，编译器会立刻抛出错误提示。｛不填写，但是只写一个default也可以｝

    */

        /*
         * 要点一：1.1 传统的写法
         */
//        IConverter<String, Integer> converter01 = new IConverter<String, Integer>() {
//            @Override
//            public Integer convert(String from) {
//                return Integer.valueOf(from);
//            }
//        };
//        Integer result = converter01.convert("321");

        // 1.2 简化后的写法：化个妆 & (form)，只有一个参数括号可以不要
//        IConverter<String, Integer> converter02 = (from) -> {
//            return Integer.valueOf(from);
//        };
//        Integer result = converter02.convert("321");

        // 1.3 继续简化，因为他的实现只有一行代码，可以更加简短
//        IConverter<String, Integer> converter03 = from -> Integer.valueOf(from);
//        Integer result = converter03.convert("321");

        // 1.4 更精简的写法:对方法和构造函数的便捷式引用
//        IConverter<Integer, String> converter04 = String::valueOf;
//        String result = converter04.convert(321);
//
//        System.out.println(result);

        /*
         * 要点二：Lambada 的作用范围
         * Lambda表达式访问外部的变量(局部变量，成员变量，静态变量，接口的默认方法),它与匿名内部类访问外部变量非常相似。
         */
        // 2.1 访问局部变量 ：从lambda表达式的外部范围读取最终局部变量num；
//        int num = 1;
//        IConverter<Integer, String> stringConverter = from -> String.valueOf(from + num);
//        String convert = stringConverter.convert(2);
//        System.out.println(convert);
        // 但是这个num是不可变值，这样改变值会报错；
//        int num = 1;
//        IConverter<Integer, String> stringConverter =
//                (from) -> String.valueOf(from + num);
//        num = 3;
        //另外在lambda表达式内部修改也是不允许的；
//        int num = 1;
//        IConverter<Integer, String> converter = (from) -> {
//            String value = String.valueOf(from + num);
//            num = 3;
//            return value;
//        };

        // 2.2 访问成员变量和静态变量
        //         对成员变量和静态变量的操作参考： Lambada4



    }

    //定义一个函数式接口
    @FunctionalInterface
    public interface IConverter<F, T> {
        // 只能包含一个抽象方法
        T convert(F from);

        //T convert2(F from);  //有两个抽象方法就会报错

        // 还可以哟一个默认的方法，但是默认的方法不能 通过 lamabda表达式访问
        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

}


