package com.pyw.base.java8;

//@FunctionalInterface  //函数式接口的申明的注解。如果加上了该注解，接口里的方法就只能有一个，否则会报错。
interface Foo{
	//函数式编程中接口有且只能定义一个方法
	//public void sayHello();
	public int add(int i,int j);
	
	public default int mul(int i,int j) {
		return i * j;
	} 
	
	public default int mul2(int i) {
		return i * i;
	}
	
	public static int div(int i,int j) {
		return i/j;
	}
}

/**
 * 
 * @author ASUS
 *1.函数式编程
 *  拷贝中括号
 *  写死右箭头
 *  落地大括号
 *  
 *  2.@FunctionalInterface
 *  
 *  3.jdk8 新特性中 default 的使用:接口中可以有default的方法体，并且可以有多个
 *  
 *  4.jdk8 新特性中 static 的使用:接口中可以有 static 的方法体，并且可以有多个
 */
public class LambadaDemo {

	public static void main(String[] args) {
			
		
		//方式一：匿名内部类
//		Foo foo = new Foo() {			
//			@Override
//			public void sayHello() {
//				// TODO Auto-generated method stub
//				System.out.println("Hello ");
//			}
//		};
		
		//方式二：Lambada的方式简写，解决匿名内部类代码冗余的问题
//		Foo foo = () -> {
//			System.out.println("Hello ");
//		};
//		foo.sayHello();
		
		Foo foo = (int x,int y) -> {
			System.out.println("Come to add ");
			return x + y;
		};
		foo.add(3,5);
		
		foo.mul(3, 5);
		
		Foo.div(15, 3);
	}
}
