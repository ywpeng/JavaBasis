package com.pyw.base.functional;

/**
 * @author pengyangwei
 * @create 2020-11-26 9:14
 */
public class Lambada4 {
    // 静态变量
    static int outerStaticNum;
    // 成员变量
    int outerNum;

    void testScopes() {
        HelloFunctionalInterface.IConverter<Integer, String> stringConverter1 = (from) -> {
            // 对成员变量赋值
            outerNum = 23;
            return String.valueOf(from);
        };

        HelloFunctionalInterface.IConverter<Integer, String> stringConverter2 = (from) -> {
            // 对静态变量赋值
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }
}
