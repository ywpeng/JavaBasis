package com.pyw.base.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author pengyangwei
 * @create 2020-11-26 8:29
 */
public class ComparaTest {

    public static void main(String[] args) {
        /**
         * 对集合类的排序的几种写法
         *  下面的例子非常好，排序场景是一个非常常见的场景，本例子结合JDK的发展历程，
         *  描述了对排序的写法的进化，层层递进，逐步精简，同时也串联讲解了相关工具类
         *  的用法。需区分可使用的场景，要以点带面，多了解相关工具类的更多用法。
         */
        //需要排序的集和对象
        List<String> names = Arrays.asList("apeter", "canna", "bmike", "dxenia");

        /*
        方式一：JDK8 之前的方式
        Collections 工具类提供了静态方法 sort 方法，入参是一个 List 集合，和一个 Comparator 比较器
         */
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });

        /*
        方式二：2.1 JDK8 之后推荐的 Lambada 表达式写法
//         */
//        Collections.sort(names,(String a,String b)->{
//            return b.compareTo(a);
//        });

        //2.2 如果方法体内只有一行代码，可以去掉方法体
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        //2.3为了追求极致，我们还可以让它再短点.
        // java.util.List 集合现在已经添加了 sort 方法。而且 Java 编译器能够根据类型推断机制判断出参数类型，这样，你连入参的类型都可以省略
//        names.sort((a, b) -> b.compareTo(a));

        //2.4 得益于Comparator接口中还提供了stack默认方法，也就是说接口中不是只可有default默认实现，还可以有静态方法
//        names.sort(Comparator.reverseOrder());


        System.out.println(names.toString());
    }





}
