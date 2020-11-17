package com.pyw.base.map;

import javafx.util.Pair;

/**
 * @author pengyangwei
 * @create 2020-11-10 17:49
 */
public class PairTest {

    public static void main(String[] args) {
        /*
         * 在javax.util包下， 有一个简单Pair类可以直接调用，
         * 用法是直接通过构造函数将所吸引类型的Key和value存入，
         * 这个key和value没有任何的对应关系类型也是任意定的。
         */
        Pair<String,String>  namePair = new Pair<>("张三","zs");
        String pk = namePair.getKey();
        String pn = namePair.getValue();
        String ps = namePair.toString();
        System.out.println(pk);
        System.out.println(pn);
        System.out.println(ps);
        System.out.println("678");

    }
}
