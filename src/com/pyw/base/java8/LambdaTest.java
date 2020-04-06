package com.pyw.base.java8;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

import com.pyw.base.java8.temp.Utils;

/**
 * 简单的使用了一下，了解了一下初步的语法。
 * @author ASUS
 *
 */
/*
 * 1.函数式编程：即可理解是将一个函数（也称为“行为”）作为一个参数进行传递。通常我们提及得更多的是面向对象编程，面向对象编程是对数据的抽象（各种各样的POJO类），而函数式编程则是对行为的抽象（将行为作为一个参数进行传递）。在JavaScript中这是很常见的一个语法特性，但在Java中将一个函数作为参数传递这却行不通，好在JDK8的出现打破了Java的这一限制。
 * 2.lambda的两种语法格式：
 * 	(parameters)  ->  expression
    (parameters)  -> {statements;}
	    语法解读：
		(parameters)，lambda表达式的参数列表，其定义方法为JAVA普通的方法相同，例如(Object a, Object b)。
		-> 箭头，是参数列表与lambda表达式主题部分的分隔符号。
		expression 单表达式
		{statements; } 语句。
 */

public class LambdaTest {

	public static void main(String[] args) {
		LambdaTest t = new LambdaTest();
		//t.testAnonInnerClassNoParm();
		t.testStaticMethod();
	}
	
	public void testAnonInnerClassNoParm() {
		// jdk 7 之前的版本写法：
//		new Thread(new Runnable() {
//		    @Override
//		    public void run() {
//		        System.out.println("Hello");
//		        System.out.println("Jimmy");
//		    }
//		}).start();
		
		new Thread(() ->  {
			System.out.println("Hello");
			System.out.println("World");
		}).start();
		
		//如果只有一条语句时，可以对代码块进行简写
		new Thread(() ->System.out.println("Hello world")).start();
		
	}
	
	public   void testStaticMethod() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(111);
		list.add(12);
		Collections.sort(list,(o1,o2) -> Utils.compare(o1,o2));		
		for (Integer i : list ) {
			System.out.println(i);
		}
	}
	
}
